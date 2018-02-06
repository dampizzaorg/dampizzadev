package com.dampizza.logic.io;
import com.dampizza.logic.dto.ProductDTO;
import java.util.List;

/**
 * Product Manager Interface
 * @author Carlos
 */
public interface ProductManagerInterface {
    public Integer createProduct(ProductDTO product);
    public Integer updateProduct(ProductDTO product);
    public Integer deleteProduct(ProductDTO product);
    public Integer deleteProduct(Integer id);
    
    public List<ProductDTO> getAllProducts();
    public List<ProductDTO> getProductByType(Integer category);
    public ProductDTO getProductById();
}
