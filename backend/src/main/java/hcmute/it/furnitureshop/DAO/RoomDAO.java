package hcmute.it.furnitureshop.DAO;

import hcmute.it.furnitureshop.Entity.Room;
import hcmute.it.furnitureshop.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomDAO {
    @Autowired
    RoomRepository roomRepository;
    public Iterable<Room> getAll(){
        return roomRepository.findAll();
    }
}
