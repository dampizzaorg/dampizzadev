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
public class OrderDeleteException extends Exception {

    /**
     * Creates a new instance of <code>OrderDeleteException</code> without
     * detail message.
     */
    public OrderDeleteException() {
    }

    /**
     * Constructs an instance of <code>OrderDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public OrderDeleteException(String msg) {
        super(msg);
    }
}
