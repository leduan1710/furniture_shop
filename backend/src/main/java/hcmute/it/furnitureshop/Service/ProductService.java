package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;

import java.util.Optional;

public interface ProductService {
    public Iterable<Product> getTop8Product();
    public Optional<Product> getProductById(Integer productId);
    public Iterable<Product> getProductByNameContaining(String name);
    Iterable<Product> getProductsByCategory(Category category);

    Iterable<Product> getProductByCategoryAndPriceDesc(Category category);
    Iterable<Product> getProductByCategoryAndPriceAsc(Category category);


}
