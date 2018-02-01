/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.model.entity;

import com.dampizza.logic.dto.UserDTO;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User Entity
 *
 * @author Carlos Santos
 */
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String email;
    private String name;
    private String surnames;
    private String address;

    public UserEntity() {

    }

    /**
     * User constructor with all attributes.
     *
     * @param username user username
     * @param email user email
     * @param name user name
     * @param surnames user surnames
     * @param address user address
     */
    public UserEntity(String username, String email, String name, String surnames, String address) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.surnames = surnames;
        this.address = address;
    }
    
    /**
     * User constructor with all attributes.
     *
     * @param username user username
     * @param email user email
     * @param name user name
     * @param surnames user surnames
     * @param address user address
     */
    public UserEntity(UserDTO user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.surnames = user.getSurnames();
        this.email = user.getEmail();
        this.address = user.getAddress();
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    @Column(updatable = false, name = "username", nullable = false, length = 50, unique = true)
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
    @Column(name = "email", nullable = false, length = 80, unique = true)
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
    @Column(name = "name", length = 80)
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
    @Column(name = "surnames", length = 80)
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
    @Column(name = "address", length = 200)
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
