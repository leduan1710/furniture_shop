package hcmute.it.furnitureshop.demo;

import hcmute.it.furnitureshop.Config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/user/check")
public class Democontroller {
    @Autowired
    JwtService jwtService;
    @GetMapping
    public String sayHello(){
        return jwtService.extractUserName(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader("Authorization")
                .replace("Bearer ",""));
    }
}
