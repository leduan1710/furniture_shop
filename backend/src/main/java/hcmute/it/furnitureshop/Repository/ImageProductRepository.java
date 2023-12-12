package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.ImageProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageProductRepository extends CrudRepository<ImageProduct,Integer> {
}
