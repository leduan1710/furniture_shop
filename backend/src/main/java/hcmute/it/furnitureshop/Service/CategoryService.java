package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Room;

import java.util.Optional;

public interface CategoryService {
    public Iterable<Category> getCategoriesByRoom(Optional<Room> room);
    public Iterable<Category> getAll();
}
