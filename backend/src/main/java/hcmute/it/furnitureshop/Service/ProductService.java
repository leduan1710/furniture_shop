package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.DTO.ProductDTO;
import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Iterable<Product> getTop8Product();
    public Optional<Product> getProductById(Integer productId);
    public Iterable<Product> getProductByNameContaining(String name);
    Iterable<Product> getProductsByCategory(Category category);
    Iterable<Product> getProductByCategoryAndPriceDesc(Category category);
    Iterable<Product> getProductByCategoryAndPriceAsc(Category category);
    Iterable<Product> findProductByRoomDesc(Integer roomId);
    Iterable<Product> findProductByRoomAsc(Integer roomId);
    Iterable<Product> findProductByRoomSale(Integer roomId);
    Optional<Product> findById(Integer productId);
    Iterable<Product> getAll();
    Optional<Product> findProductByOrderId(Integer orderId);
    public <S extends Product> void save(Product product);
    public List<ProductDTO> getAllProductsWithCategoryName();
    String updateProduct(Integer productId);
    String deleteProduct(Integer productId);
    Product createProduct(ProductDTO createProductDTO);
}
