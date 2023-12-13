package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Banner;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends CrudRepository<Banner,Integer> {
    @Transactional
    @Query(value="SELECT * FROM springserverdb.banner order by banner_id asc limit 5;", nativeQuery = true)
    public Iterable<Banner> findTop5Banner();
}
