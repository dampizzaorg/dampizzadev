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
public class ProductDataException extends Exception {

    /**
     * Creates a new instance of <code>ProductDataException</code> without
     * detail message.
     */
    public ProductDataException() {
    }

    /**
     * Constructs an instance of <code>ProductDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ProductDataException(String msg) {
        super(msg);
    }
}
