/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.exception.user;

/**
 *
 * @author Carlos Santos
 */
public class UserUpdateException extends Exception {

    /**
     * Creates a new instance of <code>UserUpdateException</code> without detail
     * message.
     */
    public UserUpdateException() {
    }

    /**
     * Constructs an instance of <code>UserUpdateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserUpdateException(String msg) {
        super(msg);
    }
}
