package hcmute.it.furnitureshop.DAO;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Room;
import hcmute.it.furnitureshop.Repository.CategoryRepository;
import hcmute.it.furnitureshop.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RoomDAO {
    @Autowired
    RoomRepository roomRepository;
    public Iterable<Room> getAll(){
        return roomRepository.findAll();
    }

    public Optional<Room> getById(Integer roomId){
        return roomRepository.findById(roomId);
    }



}
