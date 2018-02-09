/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.imp;

import com.dampizza.App;
import com.dampizza.cfg.AppConstants;
import com.dampizza.exception.user.UserCreateException;
import com.dampizza.exception.user.UserDeleteException;
import com.dampizza.exception.user.UserQueryException;
import com.dampizza.exception.user.UserUpdateException;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.logic.io.UserManagerInterface;
import com.dampizza.model.entity.CredentialEntity;
import com.dampizza.model.entity.UserEntity;
import com.dampizza.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * User Manager Method Implementations
 *
 * @author Carlos Santos
 */
public class UserManagerImp implements UserManagerInterface {

    private static final Logger logger = Logger.getLogger(UserManagerImp.class.getName());
    
    private static HashMap SESSION = new HashMap();
    
    
    
    @Override
    public Integer createUser(UserDTO user, String password) throws UserCreateException, UserQueryException {
        Integer res = 0;

        // If user is not in the database already
        if (userExists(user.getUsername()) == 2) {
            logger.log(Level.INFO, "Creating user <{0}>.", user.getUsername());
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            UserEntity userCreated = null;

            try {
                tx = session.beginTransaction();
                // Creating user.
                Long userId = (Long) session.save(new UserEntity(user));
                if (userId != null) {
                    // Retrieving created user.
                    userCreated = (UserEntity) session.get(UserEntity.class, userId);
                    res = 1;
                    logger.log(Level.INFO, "User {0} created.", user.getUsername());
                }
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                logger.severe("An error has ocurred while creating user <" + user.getUsername() + ">:");
                throw new UserCreateException("Error on createUser(): \n" + e.getMessage());
            } finally {
                session.close();
                // Creating Credential
                if (userCreated != null) {
                    createCredential(userCreated, user.getUsername(), password, AppConstants.USER_CUSTOMER);
                }

            }
        } else {
            res = 2;
            logger.log(Level.INFO, "User {0} already exists.", user.getUsername());
        }

        return res;
    }
    
    @Override
    public Integer createUser(UserDTO user, String password, Integer type) throws UserCreateException, UserQueryException {
        Integer res = 0;

        // If user is not in the database already
        if (userExists(user.getUsername()) == 2) {
            logger.log(Level.INFO, "Creating user <{0}>.", user.getUsername());
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            UserEntity userCreated = null;

            try {
                tx = session.beginTransaction();
                // Creating user.
                Long userId = (Long) session.save(new UserEntity(user));
                if (userId != null) {
                    // Retrieving created user.
                    userCreated = (UserEntity) session.get(UserEntity.class, userId);
                    res = 1;
                    logger.log(Level.INFO, "User {0} created.", user.getUsername());
                }
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                logger.severe("An error has ocurred while creating user <" + user.getUsername() + ">:");
                throw new UserCreateException("Error on createUser(): \n" + e.getMessage());
            } finally {
                session.close();
                // Creating Credential
                if (userCreated != null) {
                    createCredential(userCreated, user.getUsername(), password, type);
                }

            }
        } else {
            res = 2;
            logger.log(Level.INFO, "User {0} already exists.", user.getUsername());
        }

        return res;
    }

    @Override
    public Integer updateUser(UserDTO user) throws UserUpdateException {
        logger.log(Level.INFO, "Updating user <{0}>.", user.getUsername());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;
        String hql = "from UserEntity where credential.username = :username";

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
                res = 1;
            } else {
                res = 2;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.log(Level.SEVERE, "An error has ocurred while updating user<{0}>:", user.getUsername());
            throw new UserUpdateException("Error on updateUser(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public Integer deleteUser(Long id) throws UserDeleteException {
        logger.log(Level.INFO, "Deleting user <{0}>.", id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer res = 0;
        String hql = "from UserEntity where id = :id";

        try {
            tx = session.beginTransaction();

            // Retrieve user to update
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            UserEntity userToDelete = (UserEntity) query.uniqueResult();

            if (userToDelete != null) {
                session.delete(userToDelete);
                res = 1;
                logger.log(Level.INFO, "User id<{0}>, name<{1}> deleted.", new Object[]{id, userToDelete.getName()});
            } else {
                res = 2;
                logger.log(Level.INFO, "User id<{0}> not found.", id);
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.log(Level.SEVERE, "An error has ocurred while deleting user id<{0}>: ", id);
            throw new UserDeleteException("Error on deleteUser(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public List<UserDTO> getAllUsers() throws UserQueryException {
        logger.info("Getting list of all users.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<UserDTO> userList = new ArrayList();

        try {
            List<UserEntity> userEntities = session.createQuery("from UserEntity").list();
            if(userEntities!=null){
                userEntities.forEach(u -> userList.add(new UserDTO(u.getCredential().getUsername(), u.getName(),
                    u.getSurnames(), u.getEmail(), u.getAddress())));
            }
                
        } catch (HibernateException e) {
            logger.severe("An error has ocurred while getting users:");
            throw new UserQueryException("Error on getAllUsers(): \n" + e.getMessage());
        } finally {
            session.close();
        }

        return userList;
    }

    @Override
    public UserDTO getUserByUsername(String username) throws UserQueryException {
        logger.log(Level.INFO, "Getting user by username<{0}>", username);
        UserDTO user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from UserEntity where credential.username = :username";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            UserEntity userResult = (UserEntity) query.uniqueResult();
            if(userResult!=null){
                user = new UserDTO(userResult.getCredential().getUsername(), userResult.getName(),
                    userResult.getSurnames(), userResult.getEmail(), userResult.getAddress());
            }
            
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting user <{0}>:", username);
            throw new UserQueryException("Error on getUserByUsername(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public Integer userExists(String username) throws UserQueryException {
        logger.log(Level.INFO, "Checking if user <{0}> already exists.", username);

        Integer res = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from UserEntity where credential.username = :username";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            UserEntity userResult = (UserEntity) query.uniqueResult();

            res = userResult != null ? 1 : 2;

        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting user <{0}>:", username);
            throw new UserQueryException("Error on userExists(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public void createCredential(UserEntity user, String username, String password, Integer type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.save(new CredentialEntity(user, username, password, type));

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
        String hql = "from UserEntity where credential.username = :username and credential.password = :password";

        try {
            tx = session.beginTransaction();

            // Retrieve user to update
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            UserEntity user = (UserEntity) query.uniqueResult();

            // If loging successfull set session user
            if (user != null) {
                user.getCredential().setLastAccess(new Date());
                session.update(user.getCredential());
                // Set user logged for the app
                App.setUserLoggedIn(user);

                res = 1;
            } else {
                res = 2;
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

    public Integer changePassword(String criteria, String password) {
        Integer res = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        String hql = "from UserEntity where credential.username = :criteria or email = :criteria";

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(hql);
            query.setParameter("criteria", criteria);
            UserEntity user = (UserEntity) query.uniqueResult();
            CredentialEntity credential = user.getCredential();

            if (credential != null) {
                credential.setPassword(password);
                credential.setLastPassChange(new Date());
                session.update(credential);
                res = 1;
            } else {
                res = 2;
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
    public Integer checkStatus(String username) {
        Integer res = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        String hql = "from CredentialEntity where username = :username";

        try {
            tx = session.beginTransaction();

            // Retrieve user to update
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            CredentialEntity credential = (CredentialEntity) query.uniqueResult();

            res = credential.getCredentialType();
            // Set user logged for the app
            //App.userLoggedIn = getUserByUsername(username);


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
    public UserEntity getUserEntityByUsername(String username) throws UserQueryException {
        logger.log(Level.INFO, "Getting user entity by username<{0}>", username);
        UserEntity userResult = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from UserEntity where credential.username = :username";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            userResult = (UserEntity) query.uniqueResult();
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting user <{0}>:", username);
            throw new UserQueryException("Error on getUserEntityByUsername(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return userResult;
    }

    @Override
    public Integer emailExist(String email) throws UserQueryException {
       logger.log(Level.INFO, "Checking if email <{0}> already exists.", email);

        Integer res = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from UserEntity where email = :email";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            UserEntity userResult = (UserEntity) query.uniqueResult();

            res = userResult != null ? 1 : 2;

        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting user <{0}>:", email);
            throw new UserQueryException("Error on emailExist(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public UserEntity getUserEntityById(Long id) throws UserQueryException {
        logger.log(Level.INFO, "Getting user entity by username<{0}>", String.valueOf(id));
        UserEntity userResult = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from UserEntity where id = :id";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            userResult = (UserEntity) query.uniqueResult();
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "An error has ocurred while getting user <{0}>:", String.valueOf(id));
            throw new UserQueryException("Error on getUserEntityByUsername(): \n" + e.getMessage());
        } finally {
            session.close();
        }
        return userResult;
    }



}
