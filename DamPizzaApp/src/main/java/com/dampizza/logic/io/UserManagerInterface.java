/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.io;

import com.dampizza.logic.dto.UserDTO;
import com.dampizza.model.entity.UserEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Carlos Santos
 */
public interface UserManagerInterface {

    /**
     * Create user
     * @param user user dto object
     * @param password password string
     * @return Success: 1, Already Exists:2, Error:0
     */
    public Integer createUser(UserDTO user, String password);
    
    /**
     * 
     * @param user
     * @return 
     */
    public Integer updateUser(UserDTO user);
    public Integer deleteUser(UserDTO user);
    public List<UserDTO> getAllUsers();
    public UserDTO getUserByLogin(String username);
    
    
    
    
    

    // CREDENTIAL METHODS
    public void createCredential(UserEntity user, String username, String password);
    public Integer checkCredential(String username, String password);
    public Boolean userExist(String user);
    public Integer resetPassword(String username, String password);


}
