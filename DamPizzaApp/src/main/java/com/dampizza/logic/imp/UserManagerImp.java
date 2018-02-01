/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.imp;

import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.io.UserManagerInterface;
import com.dampizza.model.entity.UserEntity;
import com.dampizza.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

/**
 * User Manager Method Implementations
 *
 * @author Carlos Santos
 */
public class UserManagerImp implements UserManagerInterface {

    @Override
    public Integer createUser(UserDTO user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;

        try {
            tx = session.beginTransaction();
            session.save(new UserEntity(user));
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
    public List<UserDTO> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserDTO> userList = new ArrayList();
        List<UserEntity> userEntities = session.createQuery("from UserEntity").list();

        userEntities.forEach(u -> userList.add(new UserDTO(u.getUsername(), u.getName(),
                u.getSurnames(), u.getEmail(), u.getAddress())));

        return userList;
    }

    @Override
    public UserDTO getUserByLogin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer updateUser(UserDTO user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;
        String hql = "from UserEntity where username = :username";

        try {
            tx = session.beginTransaction();

            // Retrieve user to update
            Query query = session.createQuery(hql);
            query.setParameter("username", user.getUsername());
            UserEntity userToUpdate = (UserEntity) query.uniqueResult();

            if (userToUpdate != null) {
                // Update user attributes
                userToUpdate.setName(user.getName());
                userToUpdate.setSurnames(user.getSurnames());
                userToUpdate.setEmail(user.getEmail());
                userToUpdate.setAddress(user.getAddress());

                // Update user in db
                session.update(userToUpdate);
            }

            tx.commit();
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
    public Integer deleteUser(UserDTO user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;
        String hql = "from UserEntity where username = :username";

        try {
            tx = session.beginTransaction();

            // Retrieve user to update
            Query query = session.createQuery(hql);
            query.setParameter("username", user.getUsername());
            UserEntity userToDelete = (UserEntity) query.uniqueResult();

            if (userToDelete != null) {
                session.delete(userToDelete);
                System.out.println(userToDelete.getUsername()+" deleted.");
            }

            tx.commit();
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

}
