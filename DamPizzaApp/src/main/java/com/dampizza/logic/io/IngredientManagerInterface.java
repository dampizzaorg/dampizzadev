/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.io;

import com.dampizza.exception.ingredient.IngredientCreateException;
import com.dampizza.exception.ingredient.IngredientDeleteException;
import com.dampizza.exception.ingredient.IngredientQueryException;
import com.dampizza.exception.ingredient.IngredientUpdateException;
import com.dampizza.logic.dto.IngredientDTO;
import com.dampizza.model.entity.IngredientEntity;
import java.util.List;

/**
 * Ingredient manager interface.
 * CRUD methods for Ingredients.
 * @author Carlos
 */
public interface IngredientManagerInterface {
    
    /**
     * Create IngredientEntity
     * @param ingredient IngredientDTO
     * @return Success: 1, Already Exists:2, Error:0
     * @throws IngredientUpdateException, IngredientQueryException
     */
    public Integer createIngredient(IngredientDTO ingredient) throws IngredientCreateException, IngredientQueryException;
    
    /**
     * Update Ingrediente
     * @param ingredient IngredientDTO
     * @return Success: 1, Ingredient not found:2, Error:0
     */
    public Integer updateIngredient(IngredientDTO ingredient) throws IngredientUpdateException;
    
    /**
     * Delete ingredient
     * @param id ingredient id
     * @return Success: 1, Ingredient not found:2, Error:0
     * @throws IngredientUpdateException
     */
    public Integer deleteIngredient(Long id)  throws IngredientDeleteException;
    
    /**
     * Get ingredient by name
     * @param name ingredient name
     * @return IngredientDTO
     * @throws IngredientQueryException
     */
    public IngredientDTO getIngredientByName(String name) throws IngredientQueryException;
    
    /**
     * Get ingredient by id
     * @param id ingredient id
     * @return IngredientDTO
     * @throws IngredientQueryException
     */
    public IngredientDTO getIngredientById(Long id) throws IngredientQueryException;
    
    /**
     * Get all ingredients in the database
     * @return List of IngredientDTO
     * @throws IngredientQueryException
     */
    public List<IngredientDTO> getAllIngredients() throws IngredientQueryException;
    
    /**
     * Get all ingredients in the database as entities
     * @return List of IngredientEntity
     * @throws IngredientQueryException
     */
    public List<IngredientEntity> getIngredientsEntities() throws IngredientQueryException;
    
    /**
     * Get ingredient entity by id
     * @param id
     * @return IngredientEntity
     * @throws IngredientQueryException
     */
    public IngredientEntity getIngredientEntityById(Long id) throws IngredientQueryException;
    
    /**
     * Check if an ingredient is already in the database
     * @param name
     * @return 1 yes, 2 no, 0 error
     * @throws IngredientQueryException 
     */
    public Integer ingredientExists(String name) throws IngredientQueryException;
    
    /**
     * Returns the corresponding entities from a dtos list.
     * @param ingredients IngredientDTO List
     * @return IngredientEntity List
     * @throws IngredientQueryException 
     */
    public List<IngredientEntity> dtoToEntity(List<IngredientDTO> ingredients) throws IngredientQueryException;
    
    /**
     * Creates and return a list of ingredient dtos from ingredient entities.
     * @param ingredients IngredientEntity List
     * @return IngredientDTO List
     */
    public List<IngredientDTO> EntityToDTO(List<IngredientEntity> ingredients);
}
