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
public class UserCreateException extends Exception {

    /**
     * Creates a new instance of <code>UserCreateException</code> without detail
     * message.
     */
    public UserCreateException() {
    }

    /**
     * Constructs an instance of <code>UserCreateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserCreateException(String msg) {
        super(msg);
    }
}
