package cz.mazl.tul.controller;

import cz.mazl.tul.dto.AuthorDto;
import cz.mazl.tul.dto.ChatRoomDTO;
import cz.mazl.tul.dto.HistoricalMessagesDTO;
import cz.mazl.tul.dto.SimpleMessageDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static cz.mazl.tul.constants.ApplicationConstant.API_PREFIX;

@RestController
public class RoomController {

    @GetMapping(API_PREFIX + "/room/getAll")
    public List<ChatRoomDTO> getUsersChatRoom() {
        return null;
    }

    @GetMapping(API_PREFIX + "/room/messages")
    public HistoricalMessagesDTO getHistoricalMessages(@RequestParam String roomID) {
        HistoricalMessagesDTO historicalMessagesDTO = new HistoricalMessagesDTO();
        historicalMessagesDTO.setRoomId(roomID);
        historicalMessagesDTO.setSorted(true);

        SimpleMessageDTO simpleMessageDTO = new SimpleMessageDTO();
        simpleMessageDTO.setMessage("asdfasdf");
        simpleMessageDTO.setDate(new Date());
        AuthorDto authorDto = new AuthorDto();
        simpleMessageDTO.setAuthor(authorDto);
        authorDto.setName("Lukas Mazl");

        historicalMessagesDTO.setMessages(Arrays.asList(simpleMessageDTO));

        return historicalMessagesDTO;
    }
}
