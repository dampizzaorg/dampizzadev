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
public class ProductUpdateException extends Exception {

    /**
     * Creates a new instance of <code>ProductUpdateException</code> without
     * detail message.
     */
    public ProductUpdateException() {
    }

    /**
     * Constructs an instance of <code>ProductUpdateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ProductUpdateException(String msg) {
        super(msg);
    }
}
