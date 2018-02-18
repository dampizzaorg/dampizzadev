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
public class ProductQueryException extends Exception {

    /**
     * Creates a new instance of <code>ProductQueryException</code> without
     * detail message.
     */
    public ProductQueryException() {
    }

    /**
     * Constructs an instance of <code>ProductQueryException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ProductQueryException(String msg) {
        super(msg);
    }
}
