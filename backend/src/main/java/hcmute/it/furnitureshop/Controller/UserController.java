package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.Config.JwtService;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
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
        return ResponseEntity.ok("Hello User");
    }

    @RequestMapping("/addPhone/{phone}")
    public ResponseEntity<String> savePhoneOfUser(@PathVariable("phone")String phone){
        if(userService.findByPhone(phone).isPresent()){
            return ResponseEntity.status(204).body("Đã tồn tại");
        }
        Optional<User> user=userService.findByName(jwtService.extractUserName(getToken()));
        user.get().setPhone(phone);
        userService.savePhoneOfUser(user.get());
        return ResponseEntity.status(200).body("Cập nhật thành công");
    }

    @RequestMapping("/saveUser")
    public void saveUser(@RequestBody User user){
        Optional<User> userCurrent=userService.findByName(jwtService.extractUserName(getToken()));
        userCurrent.get().setName(user.getName());
        userService.saveUser(user);

    }
}
