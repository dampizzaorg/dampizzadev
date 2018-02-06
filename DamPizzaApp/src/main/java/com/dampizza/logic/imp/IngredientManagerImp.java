/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.imp;

import com.dampizza.exception.ingredient.IngredientCreateException;
import com.dampizza.exception.ingredient.IngredientDeleteException;
import com.dampizza.exception.ingredient.IngredientQueryException;
import com.dampizza.exception.ingredient.IngredientUpdateException;
import com.dampizza.logic.dto.IngredientDTO;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.io.IngredientManagerInterface;
import com.dampizza.model.entity.IngredientEntity;
import com.dampizza.model.entity.UserEntity;
import com.dampizza.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Carlos
 */
public class IngredientManagerImp implements IngredientManagerInterface {

    private static final Logger logger = Logger.getLogger(IngredientManagerImp.class.getName());

    @Override
    public Integer createIngredient(IngredientDTO ingredient) throws IngredientCreateException, IngredientQueryException {
        logger.log(Level.INFO, "Creating ingredient <{0}>.", ingredient.getName());
        Integer res = 0;

        // If ingredient is not in the database already
        if (ingredientExists(ingredient.getName()) == 2) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                session.save(new IngredientEntity(ingredient.getName(), ingredient.getPrice()));
                tx.commit();
                res = 1;
                logger.log(Level.INFO, "Ingredient {0} created.", ingredient.getName());
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                logger.severe("An error has ocurred while creating user <" + ingredient.getName() + ">:");
                throw new IngredientCreateException("Error on createUser(): \n" + e.getMessage());
            } finally {
                session.close();
            }
        } else {
            res = 2;
            logger.log(Level.INFO, "Ingredient {0} already exists.", ingredient.getName());
        }
        return res;
    }

    @Override
    public Integer updateIngredient(IngredientDTO ingredient) throws IngredientUpdateException {
        logger.log(Level.INFO, "Updating ingredient <{0}>.", ingredient.getName());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;
        String hql = "from IngredientEntity where id = :id";

        try {
            tx = session.beginTransaction();

            // Retrieve ingredient to update
            Query query = session.createQuery(hql);
            query.setParameter("id", ingredient.getId());
            IngredientEntity ingredientToUpdate = (IngredientEntity) query.uniqueResult();

            if (ingredientToUpdate != null) {
                // Update ingredient attributes
                ingredientToUpdate.setName(ingredient.getName());
                ingredientToUpdate.setPrice(ingredient.getPrice());

                // Update ingredient in db
                session.update(ingredientToUpdate);
                res = 1;
            } else {
                res = 2;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.log(Level.SEVERE, "An error has ocurred while updating ingredient<{0}>:", ingredient.getName());
            throw new IngredientUpdateException("Error on updateIngredient(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public Integer deleteIngredient(Long id) throws IngredientDeleteException {
        logger.log(Level.INFO, "Deleting ingredient <{0}>.", id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;
        String hql = "from IngredientEntity where id = :id";

        try {
            tx = session.beginTransaction();

            // Retrieve user to update
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            IngredientEntity ingredientToDelete = (IngredientEntity) query.uniqueResult();

            if (ingredientToDelete != null) {
                session.delete(ingredientToDelete);
                res = 1;
                logger.log(Level.INFO, "Ingredient id<{0}>, name<{1}> deleted.", new Object[]{id, ingredientToDelete.getName()});
            } else {
                res = 2;
                logger.log(Level.INFO, "Ingredient id<{0}> not found.", id);
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.log(Level.SEVERE, "An error has ocurred while deleting ingredient id<{0}>: ", id);
            throw new IngredientDeleteException("Error on deleteIngredient(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public IngredientDTO getIngredientByName(String name) throws IngredientQueryException {
        logger.log(Level.INFO, "Getting ingredient by name<{0}>", name);
        IngredientDTO ingredient = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from IngredientEntity where name = :name";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            IngredientEntity ingredientResult = (IngredientEntity) query.uniqueResult();
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting ingredient <{0}>:", name);
            throw new IngredientQueryException("Error on getIngredientByName(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return ingredient;
    }

    @Override
    public List<IngredientDTO> getAllIngredients() throws IngredientQueryException {
        logger.log(Level.INFO, "Getting all ingredients");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<IngredientDTO> ingredientList = new ArrayList();

        try {
            List<IngredientEntity> ingredientEntities = session.createQuery("from IngredientEntity").list();
            ingredientEntities.forEach(i -> ingredientList.add(new IngredientDTO(i.getId(), i.getName(), i.getPrice())));

        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting all ingredients");
            throw new IngredientQueryException("Error on getIngredientById(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return ingredientList;
    }

    @Override
    public IngredientDTO getIngredientById(Long id) throws IngredientQueryException {
        logger.log(Level.INFO, "Getting ingredient by id<{0}>", id);
        IngredientDTO ingredient = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from IngredientEntity where id = :id";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            IngredientEntity ingredientResult = (IngredientEntity) query.uniqueResult();

            ingredient = ingredientResult != null ? new IngredientDTO(ingredientResult.getId(), ingredientResult.getName(), ingredientResult.getPrice()) : null;
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting ingredient id<{0}>:", id);
            throw new IngredientQueryException("Error on getIngredientById(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return ingredient;
    }

    @Override
    public List<IngredientEntity> getIngredientsEntities() throws IngredientQueryException {
        logger.log(Level.INFO, "Getting all ingredient entities");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<IngredientEntity> ingredientEntities = new ArrayList();

        try {
            ingredientEntities = session.createQuery("from IngredientEntity").list();
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting ingredient entities");
            throw new IngredientQueryException("Error on getIngredientsEntities(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return ingredientEntities;
    }

    @Override
    public IngredientEntity getIngredientEntityById(Long id) throws IngredientQueryException {
        logger.log(Level.INFO, "Getting ingredient by id<{0}>", id);
        IngredientEntity ingredient = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from IngredientEntity where id = :id";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            IngredientEntity ingredientResult = (IngredientEntity) query.uniqueResult();
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting ingredient id<{0}>:", id);
            throw new IngredientQueryException("Error on getIngredientEntityById(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return ingredient;
    }

    @Override
    public Integer ingredientExists(String name) throws IngredientQueryException {
        logger.log(Level.INFO, "Checking if ingredient <{0}> already exists.", name);

        Integer res = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from IngredientEntity where name = :name";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            IngredientEntity ingredientResult = (IngredientEntity) query.uniqueResult();

            res = ingredientResult != null ? 1 : 2;

        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting ingredient <{0}>:", name);
            throw new IngredientQueryException("Error on ingredientExists(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public List<IngredientEntity> dtoToEntity(List<IngredientDTO> ingredients) throws IngredientQueryException {
        logger.log(Level.INFO, "Getting ingredient entities from a list of ingredient dto.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<IngredientEntity> filteredIngredients = null;

        if (ingredients != null) {
            List<Long> ingredientIds = new ArrayList<Long>();
            ingredients.forEach(i -> ingredientIds.add(i.getId()));

            try {
                List<IngredientEntity> ingredientEntities = session.createQuery("from IngredientEntity").list();

                /* Filtering all ingredients contained in the ingredients parameter
             * Example:
             * ingredientEntities tomate, queso, cebolla, pimientos.
             * ingredientDTO(ingredients paramater) = tomate queso.
             * filteredList = tomate, queso.
                 */
                filteredIngredients = ingredientEntities.stream().filter(i -> ingredientIds.contains(i.getId())).collect(Collectors.toList());

            } catch (HibernateException e) {
                logger.log(Level.SEVERE, "An error has ocurred while getting all ingredients");
                throw new IngredientQueryException("Error on getIngredientById(): \n" + e.getMessage());
            } finally {
                session.close();
            }
        }

        return filteredIngredients;
    }

    @Override
    public List<IngredientDTO> EntityToDTO(List<IngredientEntity> ingredients){
        logger.log(Level.INFO, "Getting ingredient dtos from a list of ingredient entities.");
        List<IngredientDTO> ingredientDtoList = new ArrayList<>();

        if (ingredients != null) {
            ingredients.forEach(i -> ingredientDtoList.add(new IngredientDTO(i.getId(), i.getName(), i.getPrice())));
        }

        return ingredientDtoList;
    }

}
