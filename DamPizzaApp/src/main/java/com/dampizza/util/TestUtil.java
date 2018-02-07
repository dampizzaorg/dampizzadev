/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.util;

import com.dampizza.cfg.AppConstants;
import com.dampizza.exception.ingredient.IngredientCreateException;
import com.dampizza.exception.ingredient.IngredientDeleteException;
import com.dampizza.exception.ingredient.IngredientQueryException;
import com.dampizza.exception.ingredient.IngredientUpdateException;
import com.dampizza.exception.order.OrderCreateException;
import com.dampizza.exception.order.OrderQueryException;
import com.dampizza.exception.product.ProductCreateException;
import com.dampizza.exception.product.ProductDeleteException;
import com.dampizza.exception.product.ProductQueryException;
import com.dampizza.exception.product.ProductUpdateException;
import com.dampizza.exception.user.UserCreateException;
import com.dampizza.exception.user.UserDeleteException;
import com.dampizza.exception.user.UserQueryException;
import com.dampizza.exception.user.UserUpdateException;
import com.dampizza.logic.dto.IngredientDTO;
import com.dampizza.logic.dto.OrderDTO;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.imp.IngredientManagerImp;
import com.dampizza.logic.imp.OrderManagerImp;
import com.dampizza.logic.imp.ProductManagerImp;
import com.dampizza.logic.imp.UserManagerImp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Util class for testing operations with the database & creating/saving mock
 * data.
 *
 * @author Carlos Santos
 */
public class TestUtil {

    private static final Logger logger = Logger.getLogger(TestUtil.class.getName());

    private UserManagerImp umi = null;
    private IngredientManagerImp imi = null;
    private ProductManagerImp pmi = null;
    private OrderManagerImp omi = null;

    public TestUtil() {
        umi = new UserManagerImp();
        imi = new IngredientManagerImp();
        pmi = new ProductManagerImp();
        omi = new OrderManagerImp();
    }

    ////////////////////////////////////////////////////////////////////////////
    //                                 USER                                   //
    ////////////////////////////////////////////////////////////////////////////
    public void testCreateUsers() {
        try {
            umi.createUser(new UserDTO("username1", "name1", "surname1", "email1@dampizza.com", "address1"), "password1");
            umi.createUser(new UserDTO("username2", "name2", "surname2", "email2@dampizza.com", "address2"), "password2");
            umi.createUser(new UserDTO("username3", "name3", "surname3", "email3@dampizza.com", "address3"), "password3");
            umi.createUser(new UserDTO("username4", "name4", "surname4", "email4@dampizza.com", "address4"), "password4");
            umi.createUser(new UserDTO("username5", "name5", "surname5", "email5@dampizza.com", "address5"), "password5");
            umi.createUser(new UserDTO("username6", "name6", "surname6", "email6@dampizza.com", "address6"), "password6");
            umi.createUser(new UserDTO("username7", "name7", "surname7", "email7@dampizza.com", "address7"), "password7");
            umi.createUser(new UserDTO("username8", "name8", "surname8", "email8@dampizza.com", "address8"), "password8");
        } catch (UserCreateException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserQueryException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdateUsers() {
        try {
            umi.updateUser(new UserDTO("username1", "name1", "lastname1UPDATED", "email1@dampizza.com", "address1"));
            umi.updateUser(new UserDTO("username7", "name7UPDATED", "lastname7", "email7@dampizza.com", "address7"));
        } catch (UserUpdateException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testDeleteUser() {
        try {
            umi.deleteUser(new Long(8));
        } catch (UserDeleteException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testGetUsers() {
        logger.info("List of Users:");
        try {
            umi.getAllUsers().forEach(u -> logger.info(u.toString()));
        } catch (UserQueryException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //                              INGREDIENT                                //
    ////////////////////////////////////////////////////////////////////////////
    public void testCreateIngredients(){
        try {
            imi.createIngredient(new IngredientDTO("Tomate", 1.50));
            imi.createIngredient(new IngredientDTO("Cebolla", 1.50));
            imi.createIngredient(new IngredientDTO("Pimiento rojo", 1.50));
            imi.createIngredient(new IngredientDTO("Pimiento verde", 1.50));
            imi.createIngredient(new IngredientDTO("Peperoni", 2.00));
            imi.createIngredient(new IngredientDTO("Jamon", 1.50));
            imi.createIngredient(new IngredientDTO("Queso", 1.50));
            imi.createIngredient(new IngredientDTO("Anchoas", 2.00));
            imi.createIngredient(new IngredientDTO("Salami", 2.00));
            imi.createIngredient(new IngredientDTO("Champiñones", 1.50));
            imi.createIngredient(new IngredientDTO("Pollo", 2.5));
            imi.createIngredient(new IngredientDTO("Carne", 2.5));
        } catch (IngredientCreateException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IngredientQueryException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testUpdateIngredients() {
        try {
            imi.updateIngredient(new IngredientDTO(new Long(1), "TomateUPDATED", 1.50));
        } catch (IngredientUpdateException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testDeleteIngredient(){
        try {
            imi.deleteIngredient(new Long(3));
        } catch (IngredientDeleteException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testGetIngredients(){
        logger.info("List of Ingredients:");
        try {
            imi.getAllIngredients().forEach(u -> logger.info(u.toString()));
        } catch (IngredientQueryException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //                                PRODUCT                                 //
    ////////////////////////////////////////////////////////////////////////////
    public void testCreateProducts(){
        try {
            List<IngredientDTO> ingredientProduct = new ArrayList<IngredientDTO>();
            
            ingredientProduct.add(new IngredientDTO(new Long(1), "Tomate", 1.50));
            ingredientProduct.add(new IngredientDTO(new Long(7), "Queso", 1.50));
            pmi.createProduct(new ProductDTO("Margarita", "", 6.00, AppConstants.PRODUCT_PIZZA, ingredientProduct));
            
            ingredientProduct.add(new IngredientDTO(new Long(4), "Pimiento verde", 1.50));
            ingredientProduct.add(new IngredientDTO(new Long(10), "Champiñones", 1.50));
            pmi.createProduct(new ProductDTO("Pepperoni", "", 6.00, AppConstants.PRODUCT_PIZZA, ingredientProduct));
            
            ingredientProduct.add(new IngredientDTO(new Long(5), "Peperoni", 2.00));
            ingredientProduct.add(new IngredientDTO(new Long(2), "Cebolla", 1.50));
            pmi.createProduct(new ProductDTO("Manhattan", "", 6.00, AppConstants.PRODUCT_PIZZA, ingredientProduct));
            
//            pmi.createProduct(new ProductDTO("Vegetal", "", 6.00, AppConstants.PRODUCT_PIZZA, null));
//            pmi.createProduct(new ProductDTO("Cesar", "", 6.00, AppConstants.PRODUCT_PIZZA, null));
            
            pmi.createProduct(new ProductDTO("Agua", "", 1.00, AppConstants.PRODUCT_DRINK, null));
            pmi.createProduct(new ProductDTO("Coca Cola", "", 2.00, AppConstants.PRODUCT_DRINK, null));
            pmi.createProduct(new ProductDTO("7up", "", 2.00, AppConstants.PRODUCT_DRINK, null));
            pmi.createProduct(new ProductDTO("Fanta Naranja", "", 2.00, AppConstants.PRODUCT_DRINK, null));
            pmi.createProduct(new ProductDTO("Heineken", "", 3.00, AppConstants.PRODUCT_DRINK, null));
        } catch (ProductCreateException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProductQueryException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testUpdateProducts(){
        try {
            pmi.updateProduct(new ProductDTO(new Long(7),"Fanta UPDATED", "", 2.00, AppConstants.PRODUCT_DRINK, null));
        } catch (ProductUpdateException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testDeleteProduct(){
        try {
            pmi.deleteProduct(new Long(5));
        } catch (ProductDeleteException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testGetProducts(){
        try {
            pmi.getAllProducts().forEach(p -> logger.info(p.toString()));
        } catch (ProductQueryException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //                                 ORDER                                  //
    ////////////////////////////////////////////////////////////////////////////
    
    public void testCreateOrder(){
        try {
            List<ProductDTO> products = new ArrayList<>();
            products.add(pmi.getProductById(new Long(1)));
            products.add(pmi.getProductById(new Long(4)));
            
            omi.createOrder(new OrderDTO(umi.getUserByUsername("username1"),
                products, umi.getUserByUsername("username2")));
        } catch (OrderCreateException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OrderQueryException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserQueryException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProductQueryException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testGetOrders(){
        try {
            omi.getAllOrders().toString();
        } catch (OrderQueryException ex) {
            Logger.getLogger(TestUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
