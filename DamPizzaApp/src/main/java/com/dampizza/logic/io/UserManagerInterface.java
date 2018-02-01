/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.io;

import com.dampizza.logic.dto.UserDTO;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Carlos Santos
 */
public interface UserManagerInterface {
    public Integer createUser(UserDTO user);
    public Integer updateUser(UserDTO user);
    public Integer deleteUser(UserDTO user);
    
    public List<UserDTO> getAllUsers();
    public UserDTO getUserByLogin();
}