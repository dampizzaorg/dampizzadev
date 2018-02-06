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
public class IngredientUpdateException extends Exception {

    /**
     * Creates a new instance of <code>IngredientUpdateException</code> without
     * detail message.
     */
    public IngredientUpdateException() {
    }

    /**
     * Constructs an instance of <code>IngredientUpdateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IngredientUpdateException(String msg) {
        super(msg);
    }
}
