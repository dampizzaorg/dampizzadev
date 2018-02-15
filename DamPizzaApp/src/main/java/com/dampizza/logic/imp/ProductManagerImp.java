/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.imp;

import com.dampizza.exception.ingredient.IngredientQueryException;
import com.dampizza.exception.product.ProductCreateException;
import com.dampizza.exception.product.ProductDeleteException;
import com.dampizza.exception.product.ProductQueryException;
import com.dampizza.exception.product.ProductUpdateException;
import com.dampizza.exception.user.UserQueryException;
import com.dampizza.logic.dto.IngredientDTO;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.io.ProductManagerInterface;
import com.dampizza.model.entity.IngredientEntity;
import com.dampizza.model.entity.ProductEntity;
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
 * Product Manager Implementation
 *
 * @author Carlos Santos
 */
public class ProductManagerImp implements ProductManagerInterface {

    private static final Logger logger = Logger.getLogger(ProductManagerImp.class.getName());
    private IngredientManagerImp imi = new IngredientManagerImp();
    private UserManagerImp umi = new UserManagerImp();

    @Override
    public Integer createProduct(ProductDTO product) throws ProductCreateException, ProductQueryException {
        Integer res = 0;

        // If product is not in the database already
        if (productExists(product.getName()) == 2) {
            logger.log(Level.INFO, "Creating product <{0}>.", product.getName());
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                System.out.println("User_id:"+String.valueOf(product.getUserId()));
                ProductEntity productEntity = null;
                
                // Creating product
                if(product.getUserId()!=0){
                    productEntity = new ProductEntity(product.getName(),
                        product.getDescription(), product.getPrice(), product.getCategory(),
                        imi.dtoToEntity(product.getIngredients()), umi.getUserEntityById(product.getUserId()));
                }else{
                        productEntity = new ProductEntity(product, imi.dtoToEntity(product.getIngredients()));     
                }
                
                
                Long productId = (Long) session.save(productEntity);
                
                tx.commit();
                if (productId != null) {
                    res = 1;
                    logger.log(Level.INFO, "Product {0} created.", product.getName());
                }
            } catch (HibernateException e) {
//                if (tx != null) {
//                    tx.rollback();
//                }
                logger.severe("An error has ocurred while creating product <" + product.getName() + ">:");
                throw new ProductCreateException("Error on createProduct(): \n" + e.getMessage());
            } catch (IngredientQueryException ex) {
                Logger.getLogger(ProductManagerImp.class.getName()).log(Level.SEVERE, "An error ocurred while retrieving ingredients list.", ex);
            } catch (UserQueryException ex) {
                Logger.getLogger(ProductManagerImp.class.getName()).log(Level.SEVERE, "An error ocurred while retrieving associated user.", ex);
            } finally {
                if(session!=null){
                    session.close();
                }  
            }
        } else {
            res = 2;
            logger.log(Level.INFO, "Product {0} already exists.", product.getName());
        }

        return res;
    }

    @Override
    public Integer updateProduct(ProductDTO product) throws ProductUpdateException {
        logger.log(Level.INFO, "Updating product <{0}>.", product.getName());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;
        String hql = "from ProductEntity where id = :id";

        try {
            tx = session.beginTransaction();

            // Retrieve product to update
            Query query = session.createQuery(hql);
            query.setParameter("id", product.getId());
            ProductEntity productToUpdate = (ProductEntity) query.uniqueResult();

            if (productToUpdate != null) {
                // Update product attributes
                productToUpdate.setName(product.getName());
                productToUpdate.setDescription(product.getDescription());
                productToUpdate.setPrice(product.getPrice());
                productToUpdate.setCategory(product.getCategory());

                List<IngredientEntity> ingredientEntities = new ArrayList<>();

                // Getting the respectives ingredients entities
                if (product.getIngredients() != null) {
                    try {
                        ingredientEntities = imi.dtoToEntity(product.getIngredients());
                    } catch (IngredientQueryException ex) {
                        Logger.getLogger(ProductManagerImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                productToUpdate.setIngredients(ingredientEntities);

                // Update ingredient in db
                session.update(productToUpdate);
                res = 1;
            } else {
                res = 2;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.log(Level.SEVERE, "An error has ocurred while updating product<{0}>:", product.getName());
            throw new ProductUpdateException("Error on updateProduct(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public Integer deleteProduct(Long id) throws ProductDeleteException {
        logger.log(Level.INFO, "Deleting product <{0}>.", id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;
        String hql = "from ProductEntity where id = :id";

        try {
            tx = session.beginTransaction();

            // Retrieve user to update
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            ProductEntity productToDelete = (ProductEntity) query.uniqueResult();

            if (productToDelete != null) {
                session.delete(productToDelete);
                res = 1;
                logger.log(Level.INFO, "Product id<{0}>, name<{1}> deleted.", new Object[]{id, productToDelete.getName()});
            } else {
                res = 2;
                logger.log(Level.INFO, "Product id<{0}> not found.", id);
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.log(Level.SEVERE, "An error has ocurred while deleting product id<{0}>: ", id);
            throw new ProductDeleteException("Error on deleteProduct(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public List<ProductDTO> getAllProducts() throws ProductQueryException {
        logger.log(Level.INFO, "Getting all products");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ProductDTO> productList = new ArrayList<>();

        try {
            List<ProductEntity> productEntities = session.createQuery("from ProductEntity where user=null").list();

            if (productEntities != null) {

                productEntities.forEach(p -> productList.add(new ProductDTO(p.getId(), p.getName(),
                        p.getDescription(), p.getPrice(), p.getCategory(), imi.EntityToDTO(p.getIngredients()), p.getUserId())));
            }

        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting all products");
            throw new ProductQueryException("Error on getAllProducts(): \n" + e.getMessage());
        } finally {
            session.close();
        }

        return productList;
    }

    @Override
    public ProductDTO getProductById(Long id) throws ProductQueryException {
        logger.log(Level.INFO, "Getting product by id<{0}>", id);
        ProductDTO product = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ProductEntity where id = :id";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            ProductEntity productResult = (ProductEntity) query.uniqueResult();

            if (productResult != null) {
                product = new ProductDTO();
                product.setId(productResult.getId());
                product.setName(productResult.getName());
                product.setDescription(productResult.getDescription());
                product.setPrice(productResult.getPrice());
                product.setCategory(productResult.getCategory());
                product.setIngredients(imi.EntityToDTO(productResult.getIngredients()));
                product.setUserId(productResult.getUserId());
            }

//            product = productResult != null ? new ProductDTO(productResult.getId(), productResult.getName(),
//                    productResult.getDescription(), productResult.getPrice(), productResult.getCategory(),
//                    imi.EntityToDTO(productResult.getIngredients()), 
//                    productResult.getUser().getId() != null ? productResult.getUser().getId() : new Long(0)) : null;
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting product id<{0}>:", id);
            throw new ProductQueryException("Error on getProductById(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return product;
    }

    @Override
    public List<ProductDTO> getProductByCategory(Integer category) throws ProductQueryException {
        logger.log(Level.INFO, "Getting list of products by category<{0}>.", category);
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ProductEntity where category = :category and user=null";
        List<ProductDTO> productList = new ArrayList<>();

        try {
            Query query = session.createQuery(hql);
            query.setParameter("category", category);
            List<ProductEntity> productEntities = (List<ProductEntity>) query.list();

            if (productEntities != null) {
                productEntities.forEach(p -> productList.add(new ProductDTO(p.getId(), p.getName(),
                        p.getDescription(), p.getPrice(), p.getCategory(), imi.EntityToDTO(p.getIngredients()), p.getUserId())));
            }

        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting products by category<{0}>", category);
            throw new ProductQueryException("Error on getProductByCategory(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return productList;
    }

    @Override
    public Integer productExists(String name) throws ProductQueryException {
        logger.log(Level.INFO, "Checking if product name<{0}> already exists.", name);
        
        Integer res = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ProductEntity where name = :name";
        System.out.println(name);
        
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            ProductEntity productResult = (ProductEntity) query.uniqueResult();
            res = productResult != null ? 1 : 2;

        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting product <{0}>:", name);
            throw new ProductQueryException("Error on productExists(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public List<ProductEntity> getProductEntities() throws ProductQueryException {
        logger.log(Level.INFO, "Getting all product entities");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ProductEntity> productEntities = new ArrayList();

        try {
            productEntities = session.createQuery("from ProductEntity").list();
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting product entities");
            throw new ProductQueryException("Error on getProductEntities(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return productEntities;
    }

    @Override
    public List<ProductEntity> dtoToEntity(List<ProductDTO> products) throws ProductQueryException {
        logger.log(Level.INFO, "Getting ingredient entities from a list of ingredient dto.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ProductEntity> filteredProducts = new ArrayList<>();

        if (products != null) {
            List<Long> productIds = new ArrayList<Long>();
            products.forEach(i -> productIds.add(i.getId()));

            try {
                List<ProductEntity> productEntities = session.createQuery("from ProductEntity").list();

                /* Filtering all ingredients contained in the ingredients parameter
             * Example:
             * ingredientEntities tomate, queso, cebolla, pimientos.
             * ingredientDTO(ingredients paramater) = tomate queso.
             * filteredList = tomate, queso.
                 */
                filteredProducts = productEntities.stream().filter(i -> productIds.contains(i.getId())).collect(Collectors.toList());

            } catch (HibernateException e) {
                logger.log(Level.SEVERE, "An error has ocurred while getting product entities from dtos.");
                throw new ProductQueryException("Error on dtoToEntity(): \n" + e.getMessage());
            } finally {
                session.close();
            }
        }

        return filteredProducts;
    }

    @Override
    public List<ProductDTO> EntityToDTO(List<ProductEntity> products) {
        logger.log(Level.INFO, "Getting ingredient dtos from a list of ingredient entities.");
        List<ProductDTO> productDtoList = new ArrayList<>();

        if (products != null) {
            products.forEach(p -> productDtoList.add(
                    new ProductDTO(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getCategory(),
                            imi.EntityToDTO(p.getIngredients()), p.getUserId())));
        }

        return productDtoList;
    }

    @Override
    public List<ProductDTO> getProductsByUserId(Long id) throws ProductQueryException {
        logger.log(Level.INFO, "Getting list of products by user id<{0}>.", id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ProductEntity where user.id = :id";
        List<ProductDTO> productList = new ArrayList<>();

        try {
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List<ProductEntity> productEntities = (List<ProductEntity>) query.list();

            if (productEntities != null) {
                productEntities.forEach(p -> productList.add(new ProductDTO(p.getId(), p.getName(),
                        p.getDescription(), p.getPrice(), p.getCategory(), imi.EntityToDTO(p.getIngredients()), p.getUserId())));
            }

        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting products by user id<{0}>", id);
            throw new ProductQueryException("Error on getProductByCategory(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return productList;
    }

}
