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
public class OrderQueryException extends Exception {

    /**
     * Creates a new instance of <code>OrderQueryException</code> without detail
     * message.
     */
    public OrderQueryException() {
    }

    /**
     * Constructs an instance of <code>OrderQueryException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public OrderQueryException(String msg) {
        super(msg);
    }
}
