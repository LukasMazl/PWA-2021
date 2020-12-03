package cz.mazl.tul.controller;

import cz.mazl.tul.bussines.Message;
import cz.mazl.tul.dto.ChatRoomDTO;
import cz.mazl.tul.dto.HistoricalMessagesDTO;
import cz.mazl.tul.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cz.mazl.tul.constants.ApplicationConstant.API_PREFIX;

@RestController
public class RoomController extends AbstractController {

    private MessageService messageService;

    @Autowired
    public RoomController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(API_PREFIX + "/room/getAll")
    public List<ChatRoomDTO> getUsersChatRoom() {
        return null;
    }

    @GetMapping(API_PREFIX + "/room/messages")
    public HistoricalMessagesDTO getHistoricalMessages(@RequestParam String roomID,@AuthenticationPrincipal OidcUser oidcUser) {
        List<Message> messages = messageService.getAllMessageForRoom(roomID, getUserId(oidcUser));
        return convertToHistoricalMessage(messages, roomID);
    }

    private HistoricalMessagesDTO convertToHistoricalMessage(List<Message> messages,
                                                             String roomId) {
        HistoricalMessagesDTO historicalMessagesDTO = new HistoricalMessagesDTO();
        historicalMessagesDTO.setSorted(true);
        historicalMessagesDTO.setRoomId(roomId);
        historicalMessagesDTO.setMessages(messages);
        if(messages.size() > 0) {
            historicalMessagesDTO.setRoomTitle(messages.get(0).getTitle());
        }
        return historicalMessagesDTO;
    }
}
