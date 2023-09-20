package hcmute.it.furnitureshop.DAO;

import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductDAO {
    @Autowired
    ProductRepository productRepository;

    public Iterable<Product> getTop8Product(){
        return productRepository.finTop8ByProductSold();
    }
}
