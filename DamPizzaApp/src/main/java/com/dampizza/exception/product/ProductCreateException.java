/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.exception.product;

/**
 *
 * @author Carlos Santos
 */
public class ProductCreateException extends Exception {

    /**
     * Creates a new instance of <code>ProductCreateException</code> without
     * detail message.
     */
    public ProductCreateException() {
    }

    /**
     * Constructs an instance of <code>ProductCreateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ProductCreateException(String msg) {
        super(msg);
    }
}
