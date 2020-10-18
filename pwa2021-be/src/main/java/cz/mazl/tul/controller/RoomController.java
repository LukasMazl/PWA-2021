package cz.mazl.tul.controller;

import cz.mazl.tul.dto.ChatRoomDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    @GetMapping("/chat/room/getAll")
    public List<ChatRoomDTO> getUsersChatRoom(){
        return null;
    }
}
