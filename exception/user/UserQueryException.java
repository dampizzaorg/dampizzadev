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
public class UserQueryException extends Exception {

    /**
     * Creates a new instance of <code>UserQueryException</code> without detail
     * message.
     */
    public UserQueryException() {
    }

    /**
     * Constructs an instance of <code>UserQueryException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserQueryException(String msg) {
        super(msg);
    }
}
