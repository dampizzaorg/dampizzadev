/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.dto;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Ingredient Data Transfer Object 
 * @author Carlos Santos
 */
public class IngredientDTO {
    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    
    public IngredientDTO(){
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
    }
    
    public IngredientDTO(String name, Double price){
        this.id = new SimpleLongProperty();
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }
    
    public IngredientDTO(Long id, String name, Double price){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
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
    
    @Override
    public String toString(){
        return this.getName();
    }
    
    
}
