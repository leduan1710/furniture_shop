package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Room;
import hcmute.it.furnitureshop.Service.CategoryService;
import hcmute.it.furnitureshop.Service.ProductService;
import hcmute.it.furnitureshop.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@RestController
public class PublicController {
    @Autowired
    RoomService roomService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;
    @GetMapping("/room")
    public Iterable<Room> getAllRoom(){
        return roomService.getAll();
    }

    @RequestMapping("/room/categories/{id}")
    public Iterable<Category> getCategoriesByRoom(@PathVariable("id") Integer roomId){
        Optional<Room> roomById= roomService.getById(roomId);
        return categoryService.getCategoriesByRoom(roomById);
    }

    @RequestMapping("/category")
    public Iterable<Category> getAllCategory(){
        return categoryService.getAll();
    }

    @RequestMapping("/product/top8Product")
    public Iterable<Product> getTop8Product(){
        return productService.getTop8Product();
    }

    @RequestMapping("/product/{productId}")
    public Optional<Product> getProductById(@PathVariable("productId") Integer productId){
        return productService.getProductById(productId);
    }

    @RequestMapping("/product/containing/{name}")
    public Iterable<Product> getProductByNameContaining(@PathVariable("name")String name){
        return productService.getProductByNameContaining(name);
    }

    @RequestMapping("/productsByCategory/{categoryId}")
    public Iterable<Product> getProductsByCategory(@PathVariable("categoryId")Integer categoryId){
        Optional<Category> category=categoryService.findById(categoryId);
        return productService.getProductsByCategory(category.get());
    }

    @RequestMapping("/getCategory/{categoryId}")
    public Optional<Category> getCategoryById(@PathVariable("categoryId")Integer categoryId){
        return categoryService.findById(categoryId);
    }
}
