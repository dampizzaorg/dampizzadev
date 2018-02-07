package com.dampizza.logic.io;
import com.dampizza.exception.product.ProductCreateException;
import com.dampizza.exception.product.ProductDeleteException;
import com.dampizza.exception.product.ProductQueryException;
import com.dampizza.exception.product.ProductUpdateException;
import com.dampizza.logic.dto.ProductDTO;
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
     * Check if product already exists in the database.
     * @param name product name
     * @return 1 yes, 2 no, 0 error
     * @throws ProductQueryException 
     */
    public Integer productExists(String name) throws ProductQueryException;
}
