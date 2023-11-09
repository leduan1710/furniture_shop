package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.Config.JwtService;
import hcmute.it.furnitureshop.Config.VNPAYService;
import hcmute.it.furnitureshop.DTO.OrderRequestDTO;
import hcmute.it.furnitureshop.DTO.ProductCheckOutDTO;
import hcmute.it.furnitureshop.DTO.UpdateUserDTO;
import hcmute.it.furnitureshop.Entity.*;
import hcmute.it.furnitureshop.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    ProductService productService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ResponseReviewService responseReviewService;

    @Autowired
    OrderService orderService;
    @Autowired
    RatingService ratingService;
    @Autowired
    VNPAYService vnpayService;
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
                return ResponseEntity.status(204).body("Không có gì");
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

    @PostMapping("/saveReview/{userId}/{productId}")
    public void saveReview(@PathVariable("userId")Integer userId,@PathVariable("productId")Integer productId,@RequestBody String content){
        Optional<User> user=userService.findById(userId);
        Optional<Product> product=productService.findById(productId);
        Review review=new Review();
        review.setUser(user.get());
        review.setProduct(product.get());
        review.setDate(new Date());
        review.setContent(content);
        reviewService.saveReview(review);
    }

    @PostMapping("/deleteReview/{reviewId}")
    public void deleteReview(@PathVariable("reviewId")Integer reviewId){
        try {
            reviewService.deleteById(reviewId);
        }catch (Exception e){

        }
    }

    @PostMapping("/saveResponseReview/{userId}/{reviewId}")
    public void saveResponseReview(@PathVariable("userId")Integer userId,@PathVariable("reviewId")Integer reviewId,@RequestBody String content){
        Optional<Review> review=reviewService.findById(reviewId);
        Optional<User> user=userService.findById(userId);
        ResponseReview responseReview=new ResponseReview();
        responseReview.setReview(review.get());
        responseReview.setContent(content);
        responseReview.setDate(new Date());
        responseReview.setUser(user.get());
        responseReviewService.save(responseReview);
    }

    @PostMapping("/deleteResponseReview/{responseReviewId}")
    public void deleteResponseReview(@PathVariable("responseReviewId")Integer responseReviewId){
        try {
            responseReviewService.deleteById(responseReviewId);
        }catch (Exception e){

        }
    }

    @GetMapping("/getResponseReview/{reviewId}")
    public Iterable<ResponseReview> getResponseReview(@PathVariable("reviewId")Integer reviewId){
        Optional<Review> review=reviewService.findById(reviewId);
        return responseReviewService.findByReview(review.get());
    }

    @PostMapping("/saveRating/{userId}/{productId}/{point}")
    public void saveRating(@PathVariable("userId")Integer userId,@PathVariable("productId")Integer productId,@PathVariable("point")Double point){
        Optional<User> user=userService.findById(userId);
        Optional<Product> product=productService.findById(productId);
        if(!ratingService.findByUserAndProduct(user.get(),product.get()).isPresent()){
            Rating rating=new Rating();
            rating.setPoint(point);
            rating.setUser(user.get());
            rating.setProduct(product.get());
            ratingService.save(rating);
        }else{
            Optional<Rating> rating=ratingService.findByUserAndProduct(user.get(),product.get());
            rating.get().setPoint(point);
            ratingService.save(rating.get());
        }

    }

    @GetMapping("/getRating/{userId}/{productId}")
    public Optional<Rating> getRating(@PathVariable("userId")Integer userId,@PathVariable("productId")Integer productId){
            Optional<User> user = userService.findById(userId);
            Optional<Product> product = productService.findById(productId);
            Optional<Rating> rating = ratingService.findByUserAndProduct(user.get(), product.get());
            return rating;


    }

    @PostMapping("/saveOrder/{productId}")
    public void saveOrder(@RequestBody OrderRequestDTO orderRequest, @PathVariable("productId")Integer productId){
        Order order=new Order();
        Optional<User> user=userService.findByName(jwtService.extractUserName(getToken()));
        Optional<Product> product=productService.findById(productId);
        order.setUser(user.get());
        order.setProduct(product.get());
        order.setState("processing");
        order.setDate(new Date());
        order.setCount(orderRequest.getCount());
        order.setPaid(orderRequest.getPaid());
        order.setNowDelivery(orderRequest.getNowDelivery());
        orderService.save(order);
    }

    @GetMapping("/findOrdersByUser")
    public Iterable<Order> findOrdersByUser(){
        Optional<User> user=userService.findByName(jwtService.extractUserName(getToken()));
        return orderService.findByUser(user.get());
    }
    @GetMapping("/findProductByOrderId/{orderId}")
    public Optional<Product> findProductByOrderId(@PathVariable("orderId")Integer orderId){
        return productService.findProductByOrderId(orderId);
    }
    @PostMapping("/pay/{price}")
    public String getPaymentUrl(@PathVariable("price") Long price, @RequestBody ProductCheckOutDTO productCheckOutDTO) throws UnsupportedEncodingException {
        String token=jwtService.extractUserName(getToken());
        return vnpayService.getPaymentUrl(price,productCheckOutDTO,token);
    }
}
