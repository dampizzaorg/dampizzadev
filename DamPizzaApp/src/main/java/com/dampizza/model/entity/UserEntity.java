package com.dampizza.model.entity;

import com.dampizza.logic.dto.UserDTO;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User Entity POJO
 * @author Carlos Santos
 */
@Entity
@Table(name = "user", schema="dampizzadb")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private CredentialEntity credential;
    
    @Column(name = "email", nullable = false, length = 80, unique = true)
    private String email;
    
    @Column(name = "name", length = 50)
    private String name;
    
    @Column(name = "surnames", length = 80)
    private String surnames;
    
    @Column(name = "address", length = 200)
    private String address;
    
    @Column(name = "active")
    private Boolean active;

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
    public UserEntity(String email, String name, String surnames, String address) {
        this.email = email;
        this.name = name;
        this.surnames = surnames;
        this.address = address;
        this.active=true;
    }
    
    /**
     * User constructor with all attributes.
     *
     * @param email user email
     * @param name user name
     * @param surnames user surnames
     * @param address user address
     */
    public UserEntity(UserDTO user) {
        this.name = user.getName();
        this.surnames = user.getSurnames();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.active=true;
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

    /**
     * @return the credential
     */
    public CredentialEntity getCredential() {
        return credential;
    }

    /**
     * @param credential the credential to set
     */
    public void setCredential(CredentialEntity credential) {
        this.credential = credential;
    }
    
    

}
