package hcmute.it.furnitureshop.DAO;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductDAO {
    @Autowired
    ProductRepository productRepository;

    public Iterable<Product> getTop8Product(){
        return productRepository.findTop8ByProductSold();
    }

    public Optional<Product> getProductById(Integer productId){
        return productRepository.findById(productId);
    }

    public Iterable<Product> getProductByNameContaining(String name){
        return productRepository.findProductsByNameContaining(name);
    }

    public  Iterable<Product> getProductsByCategory(Category category){
        return productRepository.findProductsByCategory(category);
    }
}
