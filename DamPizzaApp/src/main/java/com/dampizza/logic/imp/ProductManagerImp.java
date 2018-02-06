/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.imp;

import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.io.ProductManagerInterface;
import com.dampizza.model.entity.IngredientEntity;
import com.dampizza.model.entity.ProductEntity;
import com.dampizza.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Product Manager Implementation
 * @author Carlos
 */
public class ProductManagerImp implements ProductManagerInterface {

    private IngredientManagerImp imi = new IngredientManagerImp();

    /**
     * Create product
     * @param product
     * @return 
     */
    @Override
    public Integer createProduct(ProductDTO product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;
        List<IngredientEntity> ingredientFilteredList = null;

        try {
            if (product.getIngredients().size() > 0) {
                List<Long> ingredientIds = null;

                // Get list of all ingredients in db
                List<IngredientEntity> ingredientEntities = imi.getIngredientsEntities();

//                product.getIngredients().forEach(i -> ingredientFilteredList.add(imi.getIngredientEntityById(i.getId())));       
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        try {
            tx = session.beginTransaction();
            session.save(new ProductEntity(product, ingredientFilteredList));
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
    public Integer updateProduct(ProductDTO product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer deleteProduct(ProductDTO product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer deleteProduct(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<ProductDTO> productList = new ArrayList();
        List<ProductEntity> productEntities = session.createQuery("from ProductEntity").list();

        // TODO REVISAR INGREDIENTS
        productEntities.forEach(p -> productList.add(new ProductDTO(p.getId(), p.getName(),
                p.getDescription(), p.getPrice(), p.getCategory(), null)));

        return productList;
    }

    @Override
    public ProductDTO getProductById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductDTO> getProductByType(Integer category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;
        String hql = "from ProductEntity where category = :category";
        List<ProductDTO> productList = null;

        try {
            tx = session.beginTransaction();

            // Retrieve user to update
            Query query = session.createQuery(hql);
            query.setParameter("category", category);
            List<ProductEntity> productEntities = (List<ProductEntity>) query.list();
            
            productEntities.forEach(p -> productList.add(new ProductDTO(p.getId(), p.getName(),
                p.getDescription(), p.getPrice(), p.getCategory(), null)));

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productList;
    }

}
