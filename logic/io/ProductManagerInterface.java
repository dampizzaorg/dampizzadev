package com.dampizza.logic.io;
import com.dampizza.exception.product.ProductCreateException;
import com.dampizza.exception.product.ProductDeleteException;
import com.dampizza.exception.product.ProductQueryException;
import com.dampizza.exception.product.ProductUpdateException;
import com.dampizza.logic.dto.ProductDTO;
import com.dampizza.model.entity.ProductEntity;
import java.util.List;

/**
 * Product Manager Interface
 * @author Carlos Santos
 */
public interface ProductManagerInterface {
    
    /**
     * Create Product
     * @param product ProductDTO
     * @return Success: 1, Already Exists:2, Error:0
     * @throws ProductCreateException, ProductQueryException
     */
    public Integer createProduct(ProductDTO product) throws ProductCreateException, ProductQueryException;
    
    /**
     * Update product
     * @param product ProductDTO
     * @return Success: 1, Doesn't Exists:2, Error:0
     * @throws ProductUpdateException 
     */
    public Integer updateProduct(ProductDTO product) throws ProductUpdateException;
    
    /**
     * Delete product
     * @param id product id
     * @return Success: 1, Doesn't Exists:2, Error:0
     * @throws ProductDeleteException 
     */
    public Integer deleteProduct(Long id) throws ProductDeleteException;
    
    /**
     * Get All Products
     * @return List of ProductDTO
     * @throws ProductQueryException 
     */
    public List<ProductDTO> getAllProducts() throws ProductQueryException;
    
    /**
     * Get product by category
     * Types:
     * AppConstants.PRODUCT_PIZZA
     * AppConstants.PRODUCT_DRINK
     * @param category product category
     * @return ProductDTO List
     * @throws ProductQueryException 
     */
    public List<ProductDTO> getProductByCategory(Integer category) throws ProductQueryException;
    
    /**
     * Get product by id
     * @param id product id
     * @return matching ProductDTO
     * @throws ProductQueryException 
     */
    public ProductDTO getProductById(Long id) throws ProductQueryException;
    
    /**
     * Get custom products asociated to an user.
     * @param id userid
     * @return list of products asociated to the user.
     * @throws ProductQueryException 
     */
    public List<ProductDTO> getProductsByUserId(Long id) throws ProductQueryException;
    
    /**
     * Check if product already exists in the database.
     * @param name product name
     * @return 1 yes, 2 no, 0 error
     * @throws ProductQueryException 
     */
    public Integer productExists(String name) throws ProductQueryException;
    
    /**
     * Get all products in the database as entities
     * @return List of ProductEntity
     * @throws ProductQueryException
     */
    public List<ProductEntity> getProductEntities() throws ProductQueryException;
    
    /**
     * Returns the corresponding entities from a dtos list.
     * @param ingredients ProductDTO List
     * @return ProductEntity List
     * @throws ProductQueryException 
     */
    public List<ProductEntity> dtoToEntity(List<ProductDTO> products) throws ProductQueryException;
    
    /**
     * Creates and return a list of product dtos from product entities.
     * @param ingredients ProductEntity List
     * @return ProductDTO List
     */
    public List<ProductDTO> EntityToDTO(List<ProductEntity> products);
}
