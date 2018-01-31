/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.io;

import com.dampizza.model.entity.User;
import java.util.Collection;

/**
 *
 * @author Carlos Santos
 */
public interface UserManagerInterface {
    public Integer createUser(User user);
    public Collection<User> getUsers();
    public User getUserByLogin();
}
