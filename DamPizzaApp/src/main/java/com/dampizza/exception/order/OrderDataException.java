/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.exception.order;

/**
 *
 * @author Carlos Santos
 */
public class OrderDataException extends Exception {

    /**
     * Creates a new instance of <code>OrderDataException</code> without detail
     * message.
     */
    public OrderDataException() {
    }

    /**
     * Constructs an instance of <code>OrderDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public OrderDataException(String msg) {
        super(msg);
    }
}
