/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.model.entity;

import com.dampizza.cfg.AppConstants;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    @Basic
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Column(name = "address", nullable = false, length = 200)
    private String address;
    
    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="order_product", joinColumns=@JoinColumn(name="order_id"), inverseJoinColumns=@JoinColumn(name="product_id")) 
    private List<ProductEntity> products;
     
    @Column(name = "status", nullable = false, length = 2)
    private Integer status;
    
    @Column(name = "total", nullable = false, length = 6)
    private Double total;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity customer;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity dealer;
    
    public OrderEntity(){
        
    }
    
    public OrderEntity(UserEntity customer, String address, List<ProductEntity> products, UserEntity dealer, Double total){
        this.date= new Date();
        this.customer=customer;
        this.address=address;
        this.products=products;
        this.dealer=dealer;
        this.status=AppConstants.STATUS_PREPARING;
        this.total=total;
    }
    
    public OrderEntity(UserEntity customer, List<ProductEntity> products, UserEntity dealer, Double total){
        this.date= new Date();
        this.customer=customer;
        this.address=customer.getAddress();
        this.products=products;
        this.dealer=dealer;
        this.status=AppConstants.STATUS_PREPARING;
        this.total=total;
    }
    
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
