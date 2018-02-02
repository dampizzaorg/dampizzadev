/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.dto;

import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Carlos
 */
public class ProductDTO {

    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty category;
    private SimpleObjectProperty<IngredientDTO> ingredients;

    public ProductDTO() {
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.category = new SimpleIntegerProperty();
        this.ingredients = new SimpleObjectProperty();
    }

    public ProductDTO(Long id, String name, String description, Double price, Integer category, List<IngredientDTO> ingredients) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleIntegerProperty(category);
        this.ingredients = new SimpleObjectProperty(ingredients);
    }
    
    public ProductDTO(String name, String description, Double price, Integer category, List<IngredientDTO> ingredients) {
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleIntegerProperty(category);
        this.ingredients = new SimpleObjectProperty(ingredients);
    }

}
