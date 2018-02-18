/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.io;

import com.dampizza.exception.order.OrderCreateException;
import com.dampizza.exception.order.OrderDeleteException;
import com.dampizza.exception.order.OrderQueryException;
import com.dampizza.exception.order.OrderUpdateException;
import com.dampizza.logic.dto.OrderDTO;
import java.util.List;

/**
 * Order Manager Interface.
 * CRUD methods for OrderEntity
 * @author Carlos
 */
public interface OrderManagerInterface {
    
    /**
     * Create Order
     * @param order OrderDTO
     * @return Success: 1, Already Exists:2, Error:0
     * @throws OrderCreateException, OrderQueryException
     */
    public Integer createOrder(OrderDTO order) throws OrderCreateException, OrderQueryException;
    
    /**
     * Update order entity
     * @param order order to update
     * @return Success: 1, Order not found:2, Error:0
     * @throws OrderUpdateException 
     */
    public Integer updateOrder(OrderDTO order) throws OrderUpdateException;
    
    /**
     * deleter order entity
     * @param id order to delete id
     * @return Success: 1, Order not found:2, Error:0
     * @throws OrderDeleteException 
     */
    public Integer deleteOrder(Long id) throws OrderDeleteException;
    
    /**
     * Get List with all order entities
     * @return List OrderEntity
     * @throws OrderQueryException 
     */
    public List<OrderDTO> getAllOrders() throws OrderQueryException;
    
    /**
     * Get order entity by id
     * @param id order id
     * @return order matching id
     * @throws OrderQueryException 
     */
    public OrderDTO getOrderById(Long id) throws OrderQueryException;

	List<OrderDTO> getAllOrdersByUser() throws OrderQueryException;
}
