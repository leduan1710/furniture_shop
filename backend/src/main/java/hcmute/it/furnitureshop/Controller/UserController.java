package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.Config.JwtService;
import hcmute.it.furnitureshop.DTO.UpdateUserDTO;
import hcmute.it.furnitureshop.Entity.Favorite;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Service.FavoriteService;
import hcmute.it.furnitureshop.Service.Impl.UserServiceImpl;
import hcmute.it.furnitureshop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    ProductService productService;
    public String getToken(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader("Authorization")
                .replace("Bearer ","");
    }
    @RequestMapping("/findByName")
    public Optional<User> findByName(){
        return userService.findByName(jwtService.extractUserName(getToken()));

    }
    @RequestMapping("/check")

        public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello User");
    }

    @RequestMapping("/addPhone/{phone}")
    public ResponseEntity<String> savePhoneOfUser(@PathVariable("phone")String phone){
        try{
            if(userService.findByPhone(phone).isPresent()){
                return ResponseEntity.status(204).body("Đã tồn tại");
            }
            Optional<User> user=userService.findByName(jwtService.extractUserName(getToken()));
            user.get().setPhone(phone);
            userService.savePhoneOfUser(user.get());
            return ResponseEntity.status(200).body("Cập nhật thành công");
        }
        catch (Exception e){
            return ResponseEntity.status(200).body("Cập nhật không thành công");

        }
    }

    @PatchMapping("/saveUser")
    public void saveUser(@RequestBody UpdateUserDTO userDTO){
        try{
            Optional<User> userOld=userService.findByName(jwtService.extractUserName(getToken()));
            userOld.get().setName(userDTO.getName());
            userOld.get().setAddress(userDTO.getAddress());
            userService.saveUser(userOld.get());
        }
        catch (Exception e){

        }

    }

    @GetMapping("/favoriteByUser/{userId}")
    public Iterable<Favorite> findFavoritesByUser(@PathVariable("userId") Integer userId){
        try{
            Optional<User> user=userService.findById(userId);
            return favoriteService.findByUser(user.get());
        }catch (Exception e){
            return new ArrayList<Favorite>();
        }
    }

    @PostMapping("/saveFavorite/{userId}/{productId}")
    public void saveFavorite(@PathVariable("userId")Integer userId,@PathVariable("productId")Integer productId){
        try{
            Optional<User> user=userService.findById(userId);
            Optional<Product> product=productService.findById(productId);
            Favorite favorite=new Favorite();
            favorite.setUser(user.get());
            favorite.setProduct(product.get());
            favoriteService.saveFavorite(favorite);
        }
        catch (Exception e){
        }
    }
    @PostMapping("/deleteFavorite/{userId}/{productId}")
    public void deleteFavorite(@PathVariable("userId")Integer userId,@PathVariable("productId")Integer productId){
        try{
            Optional<User> user=userService.findById(userId);
            Optional<Product> product=productService.findById(productId);
            favoriteService.deleteByUserAndProduct(user.get(),product.get());
        }
        catch (Exception e){

        }
    }
    @GetMapping("/productByFavorite/{favoriteId}")
    public Product findProductByFavoriteId(@PathVariable("favoriteId") Integer favoriteId){
        try{
            Optional<Favorite> favorite=favoriteService.findById(favoriteId);
            return favorite.get().getProduct();
        }catch (Exception e){
            return null;
        }
    }
}
