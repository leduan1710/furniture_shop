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
    @MessageMapping("/chat/UserToAdmin")
    @SendTo("/topic/messages/UserToAdmin")
    public MessageDTO chatMessageUserToAdmin(@Payload MessageDTO messageDTO){
        messageDTO.setTimestamp(new Date());
        return messageDTO;
    }

    @MessageMapping("/chat/AdminToUser")
    @SendTo("/topic/messages/AdminToUser")
    public MessageDTO chatMessageAdminToUser(@Payload MessageDTO messageDTO){
        messageDTO.setTimestamp(new Date());
        messageDTO.setNickname("Admin");
        return messageDTO;
    }
}
