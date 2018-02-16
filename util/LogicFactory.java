/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.util;

import com.dampizza.logic.imp.OrderManagerImp;
import com.dampizza.logic.imp.ProductManagerImp;
import com.dampizza.logic.imp.UserManagerImp;
import com.dampizza.logic.imp.IngredientManagerImp;

/**
 *
 * @author Carlos Santos
 */
public class LogicFactory {
    
    private static final UserManagerImp USER_MANAGER = new UserManagerImp();
    
    private static final OrderManagerImp ORDER_MANAGER = new OrderManagerImp();
    
    private static final IngredientManagerImp INGREDIENT_MANAGER = new IngredientManagerImp();
    
    private static final ProductManagerImp PRODUCT_MANAGER = new ProductManagerImp();
    
    public static UserManagerImp getUserManager(){
            return USER_MANAGER;
    }
    
    public static OrderManagerImp getOrderManager(){
            return ORDER_MANAGER;
        
    }

    public static IngredientManagerImp getIngredientManager(){
            return INGREDIENT_MANAGER;
    }
    
    public static ProductManagerImp getProductManager(){
            return PRODUCT_MANAGER;
    }
    
    
}
