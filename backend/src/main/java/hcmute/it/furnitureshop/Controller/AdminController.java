package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.Common.ResponseModel;
import hcmute.it.furnitureshop.Config.JwtService;
import hcmute.it.furnitureshop.DTO.CreateUserDTO;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
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
        return ResponseEntity.ok("Hello Admin");
    }

    //User
    @RequestMapping("/getUsers")
    public List<User> getAll(){
        return userService.getAll();
    }

    @RequestMapping("/getUserById/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Integer userId){
        return userService.findById(userId);
    }

    @PostMapping("/createUser")
    public ResponseModel<User> createUser(@RequestBody CreateUserDTO createUserDTO){
        if(!userService.findByName(createUserDTO.getPhone()).isPresent()){
            return new ResponseModel<>(userService.createUser(createUserDTO), "Ok", "success");
        }
        else{
            return new ResponseModel<>(null, "Fail", "Đã tồn tại user trong hệ thống");
        }
    }
    //
}
