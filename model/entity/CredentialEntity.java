package com.dampizza.model.entity;

import com.dampizza.cfg.AppConstants;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * User Entity
 *
 * @author Carlos Santos
 */
@Entity
@Table(name = "credential", schema="dampizzadb")
public class CredentialEntity implements Serializable {

    @Id  
    @GeneratedValue(generator="myGenerator")  
    @GenericGenerator(name="myGenerator", strategy="foreign", parameters=@Parameter(value="user", name = "property"))  
    private Long id;

    @Column(name = "username", nullable = false, length = 80, unique = true)
    private String username;
    
    @Column(name = "password", nullable = false, length = 80)
    private String password;
    
    @Column(name = "last_access", nullable = false)
    private Date lastAccess ;
    
    @Column(name = "last_pass_change")
    private Date lastPassChange;
    
    // 1: Manager, 2: Dealer, 8: Customer
    @Column(name = "credential_type")
    private Integer credentialType;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public CredentialEntity() {

    }

    /**
     * Create credential object
     * @param username
     * @param password
     * @param user 
     */
    public CredentialEntity(UserEntity user, String username, String password) {
        this.username = username;
        this.password = password;
        this.lastAccess = new Date();
        this.lastPassChange = new Date();
        this.credentialType = AppConstants.USER_CUSTOMER;
        this.user = user;
    }
    
    /**
     * Create credential object with type parameter
     * @param username
     * @param password
     * @param user 
     */
    public CredentialEntity(UserEntity user, String username, String password, Integer credentialType) {
        this.username = username;
        this.password = password;
        this.lastAccess = new Date();
        this.lastPassChange = new Date();
        this.credentialType = credentialType;
        this.user = user;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }
    @Column(name = "last_pass_change")
    public Date getLastPassChange() {
        return lastPassChange;
    }

    public void setLastPassChange(Date lastPassChange) {
        this.lastPassChange = lastPassChange;
    }

    /**
     * @return the credentialType
     */
    public Integer getCredentialType() {
        return credentialType;
    }

    /**
     * @param credentialType the credentialType to set
     */
    public void setCredentialType(Integer credentialType) {
        this.credentialType = credentialType;
    }

    /**
     * @return the user
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }
    
    

    
}
