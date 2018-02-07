/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.imp;

import com.dampizza.exception.order.OrderCreateException;
import com.dampizza.exception.order.OrderDeleteException;
import com.dampizza.exception.order.OrderQueryException;
import com.dampizza.exception.order.OrderUpdateException;
import com.dampizza.exception.product.ProductQueryException;
import com.dampizza.exception.user.UserQueryException;
import com.dampizza.logic.dto.OrderDTO;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.io.OrderManagerInterface;
import com.dampizza.model.entity.OrderEntity;
import com.dampizza.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Carlos Santos
 */
public class OrderManagerImp implements OrderManagerInterface {

    private static final Logger logger = Logger.getLogger(OrderManagerImp.class.getName());
    private UserManagerImp umi = new UserManagerImp();
    private ProductManagerImp pmi = new ProductManagerImp();

    @Override
    public Integer createOrder(OrderDTO order) throws OrderCreateException, OrderQueryException {
        Integer res = 0;

        // If user is not in the database already
        logger.log(Level.INFO, "Creating order at {0} for customer {1}", new Object[]{order.getDate(), order.getCustomer().getUsername()});
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Creating order
            OrderEntity orderEntity = new OrderEntity(umi.getUserEntityByUsername(order.getCustomer().getUsername()),
                    pmi.dtoToEntity(order.getProducts()), umi.getUserEntityByUsername(order.getDealer().getUsername()));

            Long userId = (Long) session.save(orderEntity);
            if (userId != null) {
                res = 1;
                logger.log(Level.INFO, "Order created for {0}", order.getCustomer().getUsername());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.log(Level.SEVERE, "An error has ocurred while creating order for {0}:", order.getCustomer().getUsername());
            throw new OrderCreateException("Error on createOrder(): \n" + e.getMessage());
        } catch (ProductQueryException ex) {
            Logger.getLogger(ProductManagerImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserQueryException ex) {
            Logger.getLogger(OrderManagerImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();
        }

        return res;
    }

    @Override
    public Integer updateOrder(OrderDTO order) throws OrderUpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer deleteOrder(Long id) throws OrderDeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderDTO> getAllOrders() throws OrderQueryException {
        logger.info("Getting list of all orders.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<OrderDTO> orderList = new ArrayList();

        try {
            List<OrderEntity> orderEntities = session.createQuery("from OrderEntity").list();
            
            // For each order entity create an add an orderDTO to orderList
            orderEntities.forEach(o -> orderList.add(new OrderDTO(o.getId(),o.getDate(), 
                    new UserDTO(o.getCustomer().getCredential().getUsername(), o.getCustomer().getName(),
                                o.getCustomer().getSurnames(), o.getCustomer().getEmail(), o.getCustomer().getAddress()),
                            o.getAddress(), pmi.EntityToDTO(o.getProducts()), 
                    new UserDTO(o.getDealer().getCredential().getUsername(), o.getDealer().getName(),
                                o.getDealer().getSurnames(), o.getDealer().getEmail(), 
                                o.getDealer().getAddress()), o.getStatus())));
        } catch (HibernateException e) {
            logger.severe("An error has ocurred while getting orders:");
            throw new OrderQueryException("Error on getAllOrders(): \n" + e.getMessage());
        } finally {
            session.close();
        }

        return orderList;
    }

    @Override
    public OrderDTO getOrderById(Long id) throws OrderQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
