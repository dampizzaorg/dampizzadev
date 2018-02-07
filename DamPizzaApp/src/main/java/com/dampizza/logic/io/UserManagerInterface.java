/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.io;

import com.dampizza.exception.user.UserCreateException;
import com.dampizza.exception.user.UserDeleteException;
import com.dampizza.exception.user.UserQueryException;
import com.dampizza.exception.user.UserUpdateException;
import com.dampizza.logic.dto.UserDTO;
import com.dampizza.model.entity.UserEntity;
import java.util.Collection;
import java.util.List;

/**
 * User Manager Interface.
 * CRUD methods for User and Credential.
 * 
 * @author Carlos Santos
 */
public interface UserManagerInterface {

    /**
     * Create user Creates user from userDTO object and password, at the same
     * time a new credential is created with the user data.
     *
     * @param user UserDTO Object
     * @param password Password String
     * @return Success: 1, Already Exists:2, Error:0
     */
    public Integer createUser(UserDTO user, String password) throws UserCreateException, UserQueryException;
    
    /**
     * Update user
     * @param user UserDTO Object with modified data.
     * @return Success: 1, Doesn't Exists:2, Error:0
     */
    public Integer updateUser(UserDTO user) throws UserUpdateException;
    
    /**
     * Delete user
     * @param id user id.
     * @return Success: 1, Doesn't Exists:2, Error:0
     */
    public Integer deleteUser(Long id) throws UserDeleteException;
    
    /**
     * Get all users
     * @return List of UserDTO
     * @throws UserQueryException 
     */
    public List<UserDTO> getAllUsers() throws UserQueryException;
    
    /**
     * Get user by username
     * @param username username
     * @return User with matching id.
     * @throws UserQueryException 
     */
    public UserDTO getUserByUsername(String username) throws UserQueryException;
    
    /**
     * Check if user is already on the database.
     * @param username
     * @return 1 yes, 2 no, 0 error.
     * @throws UserQueryException 
     */
    public Integer userExists(String username) throws UserQueryException;
    
    
    

    // CREDENTIAL METHODS
    /**
     * Create credential.
     * This method is always called when a user is created.
     * @param user UserEntity
     * @param username username
     * @param password password
     */
    public void createCredential(UserEntity user, String username, String password);
    
    /**
     * Check credential.
     * Searchs for a record where the username and password are matched.
     * @param username
     * @param password
     * @return 1 yes, 2 no, 0 error. 
     */
    public Integer checkCredential(String username, String password);

    public Integer checkStatus(String username);
    public Boolean userExist(String user);

    
    /**
     * Change password
     * @param username
     * @param password
     * @return 1 Success, 2 user not found, 0 error.
     */
    public Integer changePassword(String username, String password);



}
