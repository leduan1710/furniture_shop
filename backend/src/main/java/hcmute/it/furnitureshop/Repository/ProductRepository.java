package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {

    @Transactional
    @Query(value="SELECT * FROM springserverdb.product ORDER BY number_product_sold desc limit 8", nativeQuery = true)
    Iterable<Product> finTop8ByProductSold();
}