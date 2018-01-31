/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.imp;

import com.dampizza.logic.io.UserManagerInterface;
import com.dampizza.model.entity.User;
import com.dampizza.util.HibernateUtil;
import java.util.Collection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * User Manager Method Implementations
 * @author Carlos Santos
 */
public class UserManagerImp implements UserManagerInterface{
    
    @Override
    public Integer createUser(User user) {
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = null;
      Integer res = 0;
      
      try {
         tx = session.beginTransaction();
         session.save(user); 
         tx.commit();
         res=1;
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      return res;
    }

    @Override
    public Collection<User> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserByLogin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
