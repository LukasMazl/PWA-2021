package cz.mazl.tul.controller;

import cz.mazl.tul.dto.AuthorDto;
import cz.mazl.tul.dto.SimpleMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Controller
public class ChatController {

    private SimpMessagingTemplate messageSender;

    @Autowired
    public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.messageSender = simpMessagingTemplate;
    }

    @MessageMapping("/send/{roomId}")
    public SimpleMessageDTO sendMessage(@DestinationVariable String roomId, @RequestBody String body) {

        SimpleMessageDTO simpleMessageDTO = new SimpleMessageDTO();
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAvatar("https://lh3.googleusercontent.com/-BSd2Fi_8LhA/AAAAAAAAAAI/AAAAAAAAAAA/AMZuuclvlxzoL4Sto6xNPsfclU9YSG60Tw/s96-c/photo.jpg");
        authorDto.setName("Lukas");
        simpleMessageDTO.setAuthor(authorDto);
        simpleMessageDTO.setDate(new Date());
        simpleMessageDTO.setMessage("Ahoj ty: " + Math.random());
        messageSender.convertAndSend("/topics/room/" + roomId, simpleMessageDTO);
        return null;
    }
}
