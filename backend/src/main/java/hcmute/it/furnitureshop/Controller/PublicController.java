package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.DAO.RoomDAO;
import hcmute.it.furnitureshop.Entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {
    @Autowired
    RoomDAO roomDAO;
    @GetMapping("/room")
    public Iterable<Room> getAllRoom(){
        return roomDAO.getAll();
    }
}
