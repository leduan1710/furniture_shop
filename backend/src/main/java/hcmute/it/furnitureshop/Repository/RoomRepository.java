package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    @Override
    Iterable<Room> findAll();
}
