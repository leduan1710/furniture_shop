package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.DAO.CategoryDAO;
import hcmute.it.furnitureshop.DAO.RoomDAO;
import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PublicController {
    @Autowired
    RoomDAO roomDAO;

    @Autowired
    CategoryDAO categoryDAO;
    @GetMapping("/room")
    public Iterable<Room> getAllRoom(){
        return roomDAO.getAll();
    }

    @RequestMapping("/room/categories/{id}")
    public Iterable<Category> getCategoriesByRoom(@PathVariable("id") Integer roomId){
        Optional<Room> roomById=roomDAO.getById(roomId);
        return categoryDAO.getCategoriesByRoom(roomById);
    }
}
