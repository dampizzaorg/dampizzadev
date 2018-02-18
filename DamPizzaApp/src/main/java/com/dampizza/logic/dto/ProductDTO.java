/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.dto;

import com.dampizza.cfg.AppConstants;
import java.util.Collection;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Carlos Santos
 */
public class ProductDTO {

    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty category;
    private SimpleObjectProperty<List<IngredientDTO>> ingredients;
    private SimpleLongProperty userId;
    private SimpleStringProperty url;

    public ProductDTO() {
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.category = new SimpleIntegerProperty();
        this.ingredients = new SimpleObjectProperty();
        this.userId = new SimpleLongProperty();
        this.url = new SimpleStringProperty(AppConstants.DEFAULT_PRODUCT_IMG);
    }

    /**
     * Constructor for product retrieved from database (with id)
     *
     * @param id
     * @param name
     * @param description
     * @param price
     * @param category
     * @param ingredients
     * @param userid
     */
    public ProductDTO(Long id, String name, String description, Double price, Integer category, List<IngredientDTO> ingredients, Long userid, String url) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleIntegerProperty(category);
        this.ingredients = new SimpleObjectProperty(ingredients);
        this.userId = userid != null ? new SimpleLongProperty(userid) : new SimpleLongProperty();
        this.url = (url == null || url.isEmpty()) ? 
                new SimpleStringProperty(AppConstants.DEFAULT_PRODUCT_IMG) : 
                new SimpleStringProperty(url);
    }

    /**
     * Default constructor for menu pizza
     *
     * @param name
     * @param description
     * @param price
     * @param ingredients
     */
    public ProductDTO(String name, String description, Double price, List<IngredientDTO> ingredients, String url) {
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleIntegerProperty(AppConstants.PRODUCT_PIZZA);
        this.ingredients = new SimpleObjectProperty(ingredients);
        this.userId = new SimpleLongProperty();
        this.url = (url == null || url.isEmpty()) ? 
                new SimpleStringProperty(AppConstants.DEFAULT_PRODUCT_IMG) : 
                new SimpleStringProperty(url);
    }

    /**
     * Default constructor for custom pizza
     *
     * @param name
     * @param description
     * @param price
     * @param ingredients
     */
    public ProductDTO(String name, String description, Double price, List<IngredientDTO> ingredients, Long userid) {
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleIntegerProperty(AppConstants.PRODUCT_PIZZA);
        this.ingredients = new SimpleObjectProperty(ingredients);
        this.userId = userid != null ? new SimpleLongProperty(userid) : new SimpleLongProperty();
        this.url = new SimpleStringProperty(AppConstants.DEFAULT_PRODUCT_IMG);
    }

    /**
     * Default constructor for drink
     *
     * @param name
     * @param description
     * @param price
     */
    public ProductDTO(String name, String description, Double price, String url) {
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleIntegerProperty(AppConstants.PRODUCT_DRINK);
        this.ingredients = new SimpleObjectProperty(null);
        this.userId = new SimpleLongProperty();
        this.url = (url == null || url.isEmpty()) ? 
                new SimpleStringProperty(AppConstants.DEFAULT_PRODUCT_IMG) : 
                new SimpleStringProperty(url);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id.set(id);
    }
    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description.set(description);
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price.get();
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price.set(price);
    }

    /**
     * @return the category
     */
    public Integer getCategory() {
        return category.get();
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Integer category) {
        this.category.set(category);
    }

    /**
     * @return the ingredients
     */
    public List<IngredientDTO> getIngredients() {
        return ingredients.get();
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients.set(ingredients);
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId.get();
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId.set(userId);
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url.get();
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url.set(url);
    }
    
    @Override
    public String toString() {
        return "id: " + this.getId() + ", name: " + this.getName() + ", description: " + this.getDescription()
                + ", price: " + this.getPrice() + ", category: " + this.getCategory() + ", ingredients: " + this.getIngredients().toString();
    }

    

}
