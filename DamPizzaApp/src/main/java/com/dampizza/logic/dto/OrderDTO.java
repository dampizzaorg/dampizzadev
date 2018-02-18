/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.logic.dto;

import com.dampizza.cfg.AppConstants;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Order Data Transfer Objects
 *
 * @author Carlos Santos
 */
public class OrderDTO {

    private SimpleLongProperty id;
    private SimpleObjectProperty<Date> date;
    private SimpleStringProperty address;
    private SimpleObjectProperty<List<ProductDTO>> products;
    private SimpleObjectProperty<UserDTO> customer;
    private SimpleObjectProperty<UserDTO> dealer;
    private SimpleIntegerProperty status;
    private SimpleDoubleProperty total;

    public OrderDTO() {
        this.id = new SimpleLongProperty();
        this.date = new SimpleObjectProperty<Date>();
        this.address = new SimpleStringProperty();
        this.products = new SimpleObjectProperty<List<ProductDTO>>();
        this.customer = new SimpleObjectProperty<UserDTO>();
        this.dealer = new SimpleObjectProperty<UserDTO>();
        this.status = new SimpleIntegerProperty();
        //this.total = new SimpleDoubleProperty(products.get().stream().mapToDouble(p -> p.getPrice()).sum());
        this.total = new SimpleDoubleProperty();
    }
    
    /**
     * Shopping cart constructor
     */
    public OrderDTO(UserDTO customer) {
        this.id = new SimpleLongProperty();
        this.date = new SimpleObjectProperty<Date>();
        this.address = new SimpleStringProperty(customer.getAddress());
        this.products = new SimpleObjectProperty<List<ProductDTO>>(new ArrayList<>());
        this.customer = new SimpleObjectProperty<UserDTO>(customer);
        this.dealer = new SimpleObjectProperty<UserDTO>();
        this.status = new SimpleIntegerProperty();
        this.total = new SimpleDoubleProperty();
    }
    
    public OrderDTO(UserDTO customer, List<ProductDTO> products, UserDTO dealer) {
        this.id = new SimpleLongProperty();
        this.date = new SimpleObjectProperty<Date>();

        this.address = customer != null ? new SimpleStringProperty(customer.getAddress()) 
                : new SimpleStringProperty();
        
        this.products = new SimpleObjectProperty<List<ProductDTO>>(products);
        this.customer = new SimpleObjectProperty<UserDTO>(customer);
        this.dealer = new SimpleObjectProperty<UserDTO>(dealer);
        this.status = new SimpleIntegerProperty(AppConstants.STATUS_PREPARING);
        this.total = new SimpleDoubleProperty();
        
    }
    
    public OrderDTO(UserDTO customer, String address, List<ProductDTO> products, UserDTO dealer, Integer status) {
        this.id = new SimpleLongProperty();
        this.date = new SimpleObjectProperty<Date>();
        this.address = new SimpleStringProperty(address);
        this.products = new SimpleObjectProperty<List<ProductDTO>>(products);
        this.customer = new SimpleObjectProperty<UserDTO>(customer);
        this.dealer = new SimpleObjectProperty<UserDTO>(dealer);
        this.status = new SimpleIntegerProperty(status);
        this.total = new SimpleDoubleProperty();
    }
    
    public OrderDTO(Long id, Date date, UserDTO customer, String address, List<ProductDTO> products, UserDTO dealer, Integer status) {
        this.id = new SimpleLongProperty();
        this.date = new SimpleObjectProperty<Date>(date);
        this.address = new SimpleStringProperty(address);
        this.products = new SimpleObjectProperty<List<ProductDTO>>(products);
        this.customer = new SimpleObjectProperty<UserDTO>(customer);
        this.dealer = new SimpleObjectProperty<UserDTO>(dealer);
        this.status = new SimpleIntegerProperty(status);
        this.total = new SimpleDoubleProperty();
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
//    public void setId(Long id) {
//        this.id.set(id);
//    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date.get();
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date.set(date);
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address.get();
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address.set(address);
    }

    /**
     * @return the products
     */
    public List<ProductDTO> getProducts() {
        return products.get();
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<ProductDTO> products) {
        this.products.set(products);
    }

    /**
     * @return the customer
     */
    public UserDTO getCustomer() {
        return customer.get();
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(UserDTO customer) {
        this.customer.set(customer);
    }

    /**
     * @return the dealer
     */
    public UserDTO getDealer() {
        return dealer.get();
    }

    /**
     * @param dealer the dealer to set
     */
    public void setDealer(UserDTO dealer) {
        this.dealer.set(dealer);
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status.get();
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status.set(status);
    }
    
    public String toString(){
        return "id: "+this.getId()+", date: "+this.getDate()+", address: "+this.getAddress()+
                ", products: "+this.getProducts().toString()+", customer: "+this.getCustomer()+
                ", dealer: "+this.getDealer()+", status: "+this.getStatus();
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total.get();
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total.set(total);
    }
    
    
}
