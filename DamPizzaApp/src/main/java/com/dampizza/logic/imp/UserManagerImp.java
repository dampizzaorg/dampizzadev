/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.imp;

import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.io.UserManagerInterface;
import com.dampizza.model.entity.CredentialEntity;
import com.dampizza.model.entity.UserEntity;
import com.dampizza.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
    public Integer createUser(UserDTO user, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;
        UserEntity userCreated = null;

        try {
            tx = session.beginTransaction();
            Long userId = (Long) session.save(new UserEntity(user));
            userCreated = (UserEntity) session.get(UserEntity.class, userId);
            tx.commit();
            res = 1;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            if (userCreated != null) {
                createCredential(userCreated, user.getUsername(), password);
            }

        }
        return res;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserDTO> userList = new ArrayList();

        try {
            List<UserEntity> userEntities = session.createQuery("from UserEntity").list();

            userEntities.forEach(u -> userList.add(new UserDTO(u.getCredential().getUsername(), u.getName(),
                    u.getSurnames(), u.getEmail(), u.getAddress())));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

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
                System.out.println(userToDelete.getCredential().getUsername() + " deleted.");
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
    public void createCredential(UserEntity user, String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.save(new CredentialEntity(user, username, password));

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Integer checkCredential(String username, String password) {
        Integer res = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        String hql = "from CredentialEntity where username = :username and password = :password";
        
        try {
            tx = session.beginTransaction();

            // Retrieve user to update
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            CredentialEntity credential = (CredentialEntity) query.uniqueResult();

            if (credential != null) {
                credential.setLastAccess(new Date());
                session.update(credential);
                res = 1;
            }else{
                res= 2;
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
    public Boolean userExist(String user) {
        Boolean exist=false;
        /*Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=null;
        String hql = "from UserEntity where username = :username";
        
        
        try{
            tx=session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("username", user);
            UserEntity userResult = (UserEntity) query.uniqueResult();
            
            if(userResult!=null){
                exist=true;
            }
            
        }catch(HibernateException e){
            e.printStackTrace();
        }*/
        return exist;
    }

    @Override
    public Integer resetPassword(String username, String password) {
        Integer res = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        String hql = "from CredentialEntity where username = :username";
        
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            CredentialEntity credential = (CredentialEntity) query.uniqueResult();

            if (credential != null) {
                credential.setPassword(password);
                session.update(credential);
                res = 1;
            }else{
                res= 2;
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
