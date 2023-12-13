package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Banner;
import hcmute.it.furnitureshop.Entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface BannerRepository extends CrudRepository<Banner, Integer> {
    ArrayList<Banner> findAll();
    Optional<Banner> findByProduct(Product product);
}
