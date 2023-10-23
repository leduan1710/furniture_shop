package hcmute.it.furnitureshop.Auth;



import hcmute.it.furnitureshop.Service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    UserServiceImpl userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        if(!userService.findByName(request.getUsername()).isPresent()){
            return ResponseEntity.ok(authenticationService.register(request));
        }
        else{
            return null;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


    @PostMapping("/login-gmail")
    public ResponseEntity<AuthenticationResponse> authenticateGmail(
            @RequestBody AuthenticationRequest request
    ){
        if(!userService.findByName(request.getUsername()).isPresent()){
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setUsername(request.getUsername());
            registerRequest.setPassword(request.getPassword());
            registerRequest.setName(request.getUsername());
            return ResponseEntity.ok(authenticationService.register(registerRequest));
        }
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
