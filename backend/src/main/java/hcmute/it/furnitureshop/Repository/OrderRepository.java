package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
}
