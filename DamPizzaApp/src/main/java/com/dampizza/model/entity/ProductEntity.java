package com.dampizza.model.entity;

import com.dampizza.cfg.AppConstants;
import com.dampizza.logic.dto.ProductDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Product Entity POJO
 * @author Carlos Santos
 */
@Entity
@Table(name="product", schema="dampizzadb")
public class ProductEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;
    
    @Column(name = "description", length = 200)
    private String description;
    
    @Column(name = "price")
    private Double price;
    
    @Column(name = "category")
    private Integer category;
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="product_ingredient", joinColumns=@JoinColumn(name="product_id"), inverseJoinColumns=@JoinColumn(name="ingredient_id")) 
    private List<IngredientEntity> ingredients;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
    @Column(name = "url", length = 200)
    private String url;
    
    @Column(name = "active")
    private Boolean active;
      
    public ProductEntity(){
        
    }
    
    /**
     * Create pizza
     * @param name
     * @param description
     * @param price
     * @param category
     * @param ingredients 
     * @param user
     * @param url img url
     */
    public ProductEntity(String name, String description, Double price, Integer category, List<IngredientEntity> ingredients, UserEntity user, String url){
              
        this.name=name;
        this.description=description;
        this.price=price;
        this.category=category;
        this.ingredients=ingredients;
        this.user=user;
        this.url = (url == null || url.isEmpty()) ?  AppConstants.DEFAULT_PRODUCT_IMG : url;
        this.active=true;
    }
    
    /**
     * Create drink
     * @param name
     * @param description
     * @param price
     * @param category
     */
    public ProductEntity(String name, String description, Double price, Integer category){
        this.name=name;
        this.description=description;
        this.price=price;
        this.category=category;
        this.ingredients=null;
        this.user=null;
        this.active=true;
    }
    
    public ProductEntity(ProductDTO product, List<IngredientEntity> ingredientes){
        this.name=product.getName();
        this.description=product.getDescription();
        this.price=product.getPrice();
        this.category=product.getCategory();
        this.ingredients=ingredientes;
        this.user=null;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * @return the ingredients
     */
    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * @return the user
     */
    public UserEntity getUser() {
        return user;
    }
    
    /**
     * @return the userid
     */
    public Long getUserId() {
        if(this.user==null){
            return new Long(0);
        }else{
            return this.user.getId();
        }
        
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    
    
    
    
    
}
