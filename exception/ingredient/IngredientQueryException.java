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
public class IngredientQueryException extends Exception {

    /**
     * Creates a new instance of <code>IngredientQueryException</code> without
     * detail message.
     */
    public IngredientQueryException() {
    }

    /**
     * Constructs an instance of <code>IngredientQueryException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IngredientQueryException(String msg) {
        super(msg);
    }
}
