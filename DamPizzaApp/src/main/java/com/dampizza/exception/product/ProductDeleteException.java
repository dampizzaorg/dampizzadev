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
public class ProductDeleteException extends Exception {

    /**
     * Creates a new instance of <code>ProductDeleteException</code> without
     * detail message.
     */
    public ProductDeleteException() {
    }

    /**
     * Constructs an instance of <code>ProductDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ProductDeleteException(String msg) {
        super(msg);
    }
}
