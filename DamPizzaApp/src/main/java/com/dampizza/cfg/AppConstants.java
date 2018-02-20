/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.cfg;

import com.dampizza.logic.dto.OrderDTO;

/**
 * Application constants
 * @author Carlos Santos
 */
public class AppConstants {
    
    // PRODUCT TYPES
    public static final Integer PRODUCT_PIZZA = 1;
    public static final Integer PRODUCT_DRINK = 2;
    
    // USER TYPES
    public static final Integer USER_MANAGER = 1;
    public static final Integer USER_DEALER = 2;
    public static final Integer USER_CUSTOMER = 3;
    
    // ORDER STATUS
    public static final Integer STATUS_CANCELLED = 0;
    public static final Integer STATUS_PREPARING = 1;
    public static final Integer STATUS_ONDELIVER = 2;
    public static final Integer STATUS_DELIVERED = 3;
    
    //ACTUAL ORDER
    public static final OrderDTO CURRENT_ORDER = new OrderDTO();
    
    // PRODUCT IMG URL
    public static final String DEFAULT_PRODUCT_IMG = "img/pizza_margarita.png";
    
    
}
