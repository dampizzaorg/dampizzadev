/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.exception.ingredient;

/**
 *
 * @author Carlos Santos
 */
public class IngredientCreateException extends Exception {

    /**
     * Creates a new instance of <code>IngredientCreateException</code> without
     * detail message.
     */
    public IngredientCreateException() {
    }

    /**
     * Constructs an instance of <code>IngredientCreateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IngredientCreateException(String msg) {
        super(msg);
    }
}
