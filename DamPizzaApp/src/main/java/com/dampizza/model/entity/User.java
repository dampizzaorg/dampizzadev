/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * User POJO
 * @author Carlos Santos
 */
@Entity
@Table(name="user")
public class User {
    @Id
    private String username;
    private String email;
    private String name;
    private String surnames;
    private String address;

    public User(){
    
    }
    
    /**
     * User constructor with all attributes.
     * @param username user username
     * @param email user email
     * @param name user name
     * @param surnames user surnames
     * @param address user address
     */
    public User(String username, String email, String name, String surnames, String address) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.surnames = surnames;
        this.address = address;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surnames
     */
    public String getSurnames() {
        return surnames;
    }

    /**
     * @param surnames the surnames to set
     */
    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the direction to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    
}

