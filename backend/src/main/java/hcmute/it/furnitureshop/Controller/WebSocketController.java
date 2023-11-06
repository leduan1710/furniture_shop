package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.Entity.Review;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class WebSocketController {
    @MessageMapping("/chat")
    @SendTo("/topic/reviews")
    public String review(@Payload String review){
        return review;
    }
}
