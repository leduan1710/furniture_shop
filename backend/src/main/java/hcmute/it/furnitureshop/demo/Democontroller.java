package hcmute.it.furnitureshop.demo;

import hcmute.it.furnitureshop.Config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/check")
public class Democontroller {
    @Autowired
    JwtService jwtService;
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello User");
        //return jwtService.extractUserName(token);
    }
}
