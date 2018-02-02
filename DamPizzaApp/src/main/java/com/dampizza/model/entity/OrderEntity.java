/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name="order", schema="dampizzadb")
public class OrderEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "date", nullable = false)
    private Date date;
    
    @Column(name = "address", nullable = false, length = 200)
    private String address;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "item_id")
    private List<ProductEntity> products;
    
    // Extra Ingredients
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "ingredient_id")
//    private List<IngredientEntity> ingredients;
    
    // Cancelled: 0, Preparing: 1, On delivery: 2, Delivered: 3
    @Column(name = "status", nullable = false, length = 2)
    private Integer status;
    
    @Column(name = "customer", nullable = false)
    private UserEntity customer;
    
    @Column(name = "customer")
    private UserEntity dealer;
    
    public OrderEntity(){
        
    }
    
    public OrderEntity(UserEntity customer, String address, List<ProductEntity> products){
        this.customer=customer;
        this.address=address;
        this.products=products;
        this.status=1;
    }
    
//    public OrderEntity(UserEntity customer, String address, List<ProductEntity> products, List<IngredientEntity> ingredients){
//        this.address=address;
//        this.products=products;
//        this.customer=customer;
//    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the products
     */
    public List<ProductEntity> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the customer
     */
    public UserEntity getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    /**
     * @return the dealer
     */
    public UserEntity getDealer() {
        return dealer;
    }

    /**
     * @param dealer the dealer to set
     */
    public void setDealer(UserEntity dealer) {
        this.dealer = dealer;
    }
    
    
    
}
