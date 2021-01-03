package cz.mazl.tul.controller;

import cz.mazl.tul.bussines.Message;
import cz.mazl.tul.dto.*;
import cz.mazl.tul.service.message.MessageService;
import cz.mazl.tul.service.room.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cz.mazl.tul.constants.ApplicationConstant.API_PREFIX;

@RestController
public class RoomController extends AbstractController {

    private MessageService messageService;
    private ChatroomService chatroomService;

    @Autowired
    public RoomController(MessageService messageService, ChatroomService chatroomService) {
        this.messageService = messageService;
        this.chatroomService = chatroomService;
    }

    @GetMapping(API_PREFIX + "/room/all")
    public List<ChatRoomDTO> getUsersChatRoom() {
        return null;
    }

    @GetMapping(API_PREFIX + "/room/messages")
    public HistoricalMessagesDTO getHistoricalMessages(@RequestParam String roomID,@AuthenticationPrincipal OidcUser oidcUser) {
        List<Message> messages = messageService.getAllMessageForRoom(roomID, getUserId(oidcUser));
        return convertToHistoricalMessage(messages, roomID);
    }

    @PostMapping(API_PREFIX + "/room/create")
    public CreateRoomDto creteNewRoom(@RequestBody CreateNewRoomDto createNewRoomDto,
                                      @AuthenticationPrincipal OidcUser principal) {
        String authorId = principal.getEmail();
        List<String> userList = createNewRoomDto.getUserList();
        String roomName = createNewRoomDto.getRoomName();
        String roomId = chatroomService.createNewRoom(authorId, roomName, userList);
        CreateRoomDto createRoomDto = new CreateRoomDto();
        createRoomDto.setRoomId(roomId);
        return createRoomDto;
    }

    @PostMapping(API_PREFIX + "/room/prepare")
    public PreparedChatRoomDto preparedChatRoom(@RequestBody PrepareChatRoomDto createNewRoomDto,
                                                @AuthenticationPrincipal OidcUser principal) {
        return chatroomService.prepareRoom(getUserId(principal), createNewRoomDto.getUserId());
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
