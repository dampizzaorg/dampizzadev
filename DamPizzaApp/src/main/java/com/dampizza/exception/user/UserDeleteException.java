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
public class UserDeleteException extends Exception {

    /**
     * Creates a new instance of <code>UserDeleteException</code> without detail
     * message.
     */
    public UserDeleteException() {
    }

    /**
     * Constructs an instance of <code>UserDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserDeleteException(String msg) {
        super(msg);
    }
}
