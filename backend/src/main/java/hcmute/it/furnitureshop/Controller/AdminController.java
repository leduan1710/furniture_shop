package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.DTO.ResponseDTO;
import hcmute.it.furnitureshop.Config.JwtService;
import hcmute.it.furnitureshop.DTO.CreateUserDTO;
import hcmute.it.furnitureshop.DTO.UserDTO;
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
    public ResponseDTO<UserDTO> getUserById(@PathVariable("userId") Integer userId){
        UserDTO userDTO = userService.getById(userId);
        if(userDTO != null){
            return new ResponseDTO<>(userDTO, "Ok", "Lấy thông tin người dùng thành công");
        }
        else{
            return new ResponseDTO<>(null, "Fail", "Không tồn tại người dùng");
        }
    }

    @PostMapping("/createUser")
    public ResponseDTO<?> createUser(@RequestBody CreateUserDTO createUserDTO){
        User user = userService.createUser(createUserDTO);
        if(user != null){
            return new ResponseDTO<>(userService.createUser(createUserDTO), "Ok", "Thêm người dùng thành công");
        }
        else{
            return new ResponseDTO<>(null, "Fail", "Thêm người dùng thất bại ! Đã tồn tại user trong hệ thống");
        }
    }

    @PostMapping("/updateUserStatus/{userId}")
    public ResponseDTO<?> updateUser(@PathVariable("userId") Integer userId){
        String message = userService.updateStatusUser(userId);
        return new ResponseDTO<>(null, "Ok", message);
    }

    @RequestMapping("/deleteUser/{userId}")
    public ResponseDTO<?> deleteUser(@PathVariable("userId") Integer userId){
        String message = userService.deleteUser(userId);
        return new ResponseDTO<>(null, "Ok", message);
    }
    //
}
