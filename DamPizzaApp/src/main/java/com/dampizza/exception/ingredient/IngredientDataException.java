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
public class IngredientDataException extends Exception {

    /**
     * Creates a new instance of <code>IngredientDataException</code> without
     * detail message.
     */
    public IngredientDataException() {
    }

    /**
     * Constructs an instance of <code>IngredientDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IngredientDataException(String msg) {
        super(msg);
    }
}
