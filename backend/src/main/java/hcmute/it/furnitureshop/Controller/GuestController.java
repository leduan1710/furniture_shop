package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.Config.VNPAYService;
import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Review;
import hcmute.it.furnitureshop.Entity.Room;
import hcmute.it.furnitureshop.Service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping("/guest")
public class GuestController {
    @Autowired
    RoomService roomService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;
    @GetMapping("/room")
    public Iterable<Room> getAllRoom(){
        return roomService.getAll();
    }
    @GetMapping("/room/{roomId}")
    public Optional<Room> getRoomById(@PathVariable("roomId")Integer roomId){
        return roomService.getById(roomId);
    }
    @RequestMapping("/room/categories/{id}")
    public Iterable<Category> getCategoriesByRoom(@PathVariable("id") Integer roomId){
        Optional<Room> roomById= roomService.getById(roomId);
        return categoryService.getCategoriesByRoom(roomById);
    }
    @RequestMapping("/room/products/{id}")
    public Iterable<Product> getProductsByRoom(@PathVariable("id") Integer roomId){
        Optional<Room> roomById= roomService.getById(roomId);
        Iterable<Category> categories=categoryService.getCategoriesByRoom(roomById);
        ArrayList<Product> products = new ArrayList<>();
        categories.forEach(category -> {
            products.addAll((Collection<? extends Product>) productService.getProductsByCategory(category));
        });
        return products;
    }

    @RequestMapping("/category")
    public Iterable<Category> getAllCategory(){
        return categoryService.getAll();
    }

    @RequestMapping("/products")
    public Iterable<Product> getAllProduct(){
        return productService.getAll();
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
    @RequestMapping("/productsByCategoryDesc/{categoryId}")
    public Iterable<Product> getProductsByCategoryAndPriceDesc(@PathVariable("categoryId")Integer categoryId){
        Optional<Category> category=categoryService.findById(categoryId);
        return productService.getProductByCategoryAndPriceDesc(category.get());
    }
    @RequestMapping("/productsByCategoryAsc/{categoryId}")
    public Iterable<Product> getProductsByCategoryAndPriceAsc(@PathVariable("categoryId")Integer categoryId){
        Optional<Category> category=categoryService.findById(categoryId);
        return productService.getProductByCategoryAndPriceAsc(category.get());
    }
    @RequestMapping("/productsByCategoryOrderDiscount/{categoryId}")
    public Iterable<Product> getProductsByCategoryAndDiscount(@PathVariable("categoryId")Integer categoryId){
        Optional<Category> category=categoryService.findById(categoryId);
        Iterable<Product> products= productService.getProductsByCategory(category.get());
        ArrayList<Product> productsHaveDisCount = new ArrayList<>();
        products.forEach(product -> {
            if(product.getDiscount()!=null){
                productsHaveDisCount.add(product);
            }
        });
        return productsHaveDisCount;
    }
    @RequestMapping("/getCategory/{categoryId}")
    public Optional<Category> getCategoryById(@PathVariable("categoryId")Integer categoryId){
        return categoryService.findById(categoryId);
    }

    @RequestMapping("/ProductDescByRoom/{roomId}")
    public Iterable<Product> getProductDescByRoom(@PathVariable("roomId") Integer roomId){
        return productService.findProductByRoomDesc(roomId);
    }
    @RequestMapping("/ProductAscByRoom/{roomId}")
    public Iterable<Product> getProductAscByRoom(@PathVariable("roomId") Integer roomId){
        return productService.findProductByRoomAsc(roomId);
    }
    @RequestMapping("/ProductSaleByRoom/{roomId}")
    public Iterable<Product> getProductSaleByRoom(@PathVariable("roomId") Integer roomId){
        Iterable<Product> products= productService.findProductByRoomSale(roomId);
        ArrayList<Product> productsHaveDisCount = new ArrayList<>();
        products.forEach(product -> {
            if(product.getDiscount()!=null){
                productsHaveDisCount.add(product);
            }
        });
        return productsHaveDisCount;
    }

    @RequestMapping("/checkPhone/{phone}")
    public boolean checkPhone(@PathVariable("phone")String phone){
        if(userService.findByName(phone).isPresent()){
            return true;
        }else{
            return false;
        }
    }
    @GetMapping("/reviewByProduct/{productId}")
    public Iterable<Review> findReviewsByProduct(@PathVariable("productId")Integer productId){
        Optional<Product> product=productService.findById(productId);
        return reviewService.findByProduct(product.get());
    }

}
