package hcmute.it.furnitureshop.DAO;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Room;
import hcmute.it.furnitureshop.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryDAO {
    @Autowired
    CategoryRepository categoryRepository;
    public Iterable<Category> getCategoriesByRoom(Optional<Room> room){
        return categoryRepository.findCategoriesByRoom(room);
    }

    public Iterable<Category> getAll(){
        return  categoryRepository.findAll();
    }

    public Optional<Category> findById(Integer categoryId){
        return categoryRepository.findById(categoryId);
    }
}
