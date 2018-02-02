/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.io;

import com.dampizza.logic.dto.IngredientDTO;
import java.util.List;

/**
 * Ingredient manager interface
 * @author Carlos
 */
public interface IngredientManagerInterface {
    public Integer createIngredient(IngredientDTO ingredient);
    public Integer updateIngredient(IngredientDTO ingredient);
    public Integer deletIngredient(String name);
    
    public IngredientDTO getIngredientByName(String name);
    public List<IngredientDTO> getIngredients();
    
}
