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
import com.dampizza.exception.user.UserUpdateException;
import com.dampizza.logic.dto.OrderDTO;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.io.OrderManagerInterface;
import com.dampizza.model.entity.OrderEntity;
import com.dampizza.model.entity.UserEntity;
import com.dampizza.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            System.out.println(order.toString());
            UserEntity dealer = order.getDealer() != null
                    ? umi.getUserEntityByUsername(order.getDealer().getUsername())
                    : null;

            // Creating order
            OrderEntity orderEntity = new OrderEntity(umi.getUserEntityByUsername(order.getCustomer().getUsername()),
                    pmi.dtoToEntity(order.getProducts()),
                    dealer,
                    order.getTotal());

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
    public Integer updateOrder(OrderDTO order) throws OrderUpdateException, OrderQueryException {
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
            orderEntities.forEach(o -> orderList.add(new OrderDTO(o.getId(), o.getDate(),
                    new UserDTO(o.getCustomer().getId(), o.getCustomer().getCredential().getUsername(), o.getCustomer().getName(),
                            o.getCustomer().getSurnames(), o.getCustomer().getEmail(), o.getCustomer().getAddress()),
                    o.getAddress(), pmi.EntityToDTO(o.getProducts()),
                    new UserDTO(
                            o.getDealer()!=null ? o.getDealer().getId() : null,
                            o.getDealer()!=null ? o.getDealer().getCredential().getUsername() : null,
                            o.getDealer()!=null ? o.getDealer().getName() : null,
                            o.getDealer()!=null ? o.getDealer().getSurnames() : null,
                            o.getDealer()!=null ? o.getDealer().getEmail() : null,
                            o.getDealer()!=null ? o.getDealer().getAddress() : null),
                            o.getStatus(), o.getTotal())));
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

    @Override
    public Integer updateStatus(Long id, Integer status) throws OrderUpdateException, OrderQueryException {
        logger.log(Level.INFO, "Updating order <{0}> status to <{1}>", new Object[]{id, status});
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;

        try {
            tx = session.beginTransaction();
            OrderEntity orderToUpdate = (OrderEntity) session.get(OrderEntity.class, id);

            if (orderToUpdate != null) {
                orderToUpdate.setStatus(status);
                session.update(orderToUpdate);
                res = 1;
            } else {
                res = 2;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.log(Level.SEVERE, "An error has ocurred while updating order<{0}>:", id);
            throw new OrderUpdateException("Error on updateStatus(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(Integer status) throws OrderQueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer updateDealer(Long id, Long dealerId) throws OrderUpdateException, OrderQueryException {
        logger.log(Level.INFO, "Set dealer id<{0}> to order<{1}>", new Object[]{dealerId, id});
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;

        try {
            tx = session.beginTransaction();
            OrderEntity orderToUpdate = (OrderEntity) session.get(OrderEntity.class, id);
            UserEntity dealer = (UserEntity) session.get(UserEntity.class, dealerId);

            if (orderToUpdate != null && dealer != null) {
                orderToUpdate.setDealer(dealer);
                session.update(orderToUpdate);
                res = 1;
            } else {
                res = 2;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.log(Level.SEVERE, "An error has ocurred while updating order<{0}>:", id);
            throw new OrderUpdateException("Error on updateStatus(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

}
