package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.DTO.MessageDTO;
import hcmute.it.furnitureshop.Entity.Review;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

@Controller
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class WebSocketController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public MessageDTO chatMessage(@Payload MessageDTO messageDTO){
        messageDTO.setTimestamp(new Date());
        return messageDTO;
    }
}
