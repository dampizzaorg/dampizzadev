/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.imp;

import com.dampizza.logic.dto.IngredientDTO;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.io.IngredientManagerInterface;
import com.dampizza.model.entity.IngredientEntity;
import com.dampizza.model.entity.UserEntity;
import com.dampizza.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Carlos
 */
public class IngredientManagerImp implements IngredientManagerInterface{

    @Override
    public Integer createIngredient(IngredientDTO ingredient) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;

        try {
            tx = session.beginTransaction();
            session.save(new IngredientEntity(ingredient.getName(), ingredient.getPrice()));
            tx.commit();
            res = 1;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();     
        }
        return res;
    }

    @Override
    public Integer updateIngredient(IngredientDTO ingredient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer deletIngredient(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IngredientDTO getIngredientByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IngredientDTO> getIngredients() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<IngredientDTO> ingredientList = new ArrayList();
        List<IngredientEntity> ingredientEntities = session.createQuery("from IngredientEntity").list();

        ingredientEntities.forEach(i -> ingredientList.add(new IngredientDTO(i.getId(), i.getName(), i.getPrice())));

        return ingredientList;
    }
    
}
