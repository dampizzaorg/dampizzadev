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
public class IngredientDeleteException extends Exception {

    /**
     * Creates a new instance of <code>IngredientDeleteException</code> without
     * detail message.
     */
    public IngredientDeleteException() {
    }

    /**
     * Constructs an instance of <code>IngredientDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IngredientDeleteException(String msg) {
        super(msg);
    }
}
