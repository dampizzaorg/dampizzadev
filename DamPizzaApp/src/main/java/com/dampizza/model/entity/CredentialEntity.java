/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.model.entity;

import com.dampizza.logic.dto.UserDTO;
import java.io.Serializable;
import java.util.Date;
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
@Table(name = "credentials")
public class CredentialEntity implements Serializable {

    @Id
    private long userId;

    private String username;
    private String password;
    private Date lastAccess ;
    private Date lastPassChange;
    private Integer status;
    private Boolean active;

    public CredentialEntity() {

    }

    /**
     * 
     * @param username
     * @param email
     * @param name
     * @param surnames
     * @param address 
     */
    public CredentialEntity(String username, String password, Date lastAccess, Date lastPassChange, Integer status, Boolean active) {
        this.username = username;
        this.password = password;
        this.lastAccess = lastAccess;
        this.lastPassChange = lastPassChange;
        this.status = status;
        this.active = active;
    }
    
   /**
    * 
    * @param user 
    */
    
    public CredentialEntity(UserDTO user,Integer status,String Password) {
        this.username = user.getUsername();
        this.password = password;
        this.lastAccess = new Date();
        this.lastPassChange = new Date();
       this.status = status;
       this.active= true;
    }

    /**
     * @return the id
     */
    public long getId() {
        return userId;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.userId = userId;
    }
    @Column(name = "User_id", nullable = false, length = 80, unique = true)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    @Column(name = "username", nullable = false, length = 80, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "password", nullable = false, length = 80)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "lastAcess", nullable = false)
    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }
    @Column(name = "lastPassChange")
    public Date getLastPassChange() {
        return lastPassChange;
    }

    public void setLastPassChange(Date lastPassChange) {
        this.lastPassChange = lastPassChange;
    }
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    
}
