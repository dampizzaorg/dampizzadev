package com.dampizza.logic.io;

import com.dampizza.exception.order.OrderCreateException;
import com.dampizza.exception.order.OrderDeleteException;
import com.dampizza.exception.order.OrderQueryException;
import com.dampizza.exception.order.OrderUpdateException;
import com.dampizza.logic.dto.OrderDTO;
import com.dampizza.logic.dto.UserDTO;
import java.util.List;

/**
 * Order Manager Interface.
 * CRUD methods for OrderEntity
 * @author Carlos Santos
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
    public Integer updateOrder(OrderDTO order) throws OrderUpdateException, OrderQueryException;
    
    /**
     * Update order status
     * @param order order to update
     * @return Success: 1, Order not found:2, Error:0
     * @throws OrderUpdateException 
     */
    public Integer updateStatus(Long id, Integer status) throws OrderUpdateException, OrderQueryException;
    
    /**
     * 
     * @param id order id
     * @param dealerId dealer id
     * @return 1 success, 2 order not found, 0 error.
     * @throws OrderUpdateException
     * @throws OrderQueryException 
     */
    public Integer updateDealer(Long id, Long dealerId) throws OrderUpdateException, OrderQueryException;
    
    /**
     * Delete order entity
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
     * Get orders by status.
     * @param status order status
     * @return OrderDTO List
     * @throws OrderQueryException 
     */
    public List<OrderDTO> getOrdersByStatus(Integer status) throws OrderQueryException;
    
    /**
     * Get order entity by id
     * @param id order id
     * @return order matching id
     * @throws OrderQueryException 
     */
    public OrderDTO getOrderById(Long id) throws OrderQueryException;
    
    /**
     * Get orders by user
     * @return OrderDTO List
     * @throws OrderQueryException 
     */
    public List<OrderDTO> getAllOrdersByUser() throws OrderQueryException;
}
