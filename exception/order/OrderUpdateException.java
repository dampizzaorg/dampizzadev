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
public class OrderUpdateException extends Exception {

    /**
     * Creates a new instance of <code>OrderUpdateException</code> without
     * detail message.
     */
    public OrderUpdateException() {
    }

    /**
     * Constructs an instance of <code>OrderUpdateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public OrderUpdateException(String msg) {
        super(msg);
    }
}
