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
public class UserDataException extends Exception {

    /**
     * Creates a new instance of <code>UserDataException</code> without detail
     * message.
     */
    public UserDataException() {
    }

    /**
     * Constructs an instance of <code>UserDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserDataException(String msg) {
        super(msg);
    }
}
