package cz.mazl.tul.service.room;

import cz.mazl.tul.dto.ChatRoomDTO;
import cz.mazl.tul.dto.PreparedChatRoomDto;

import java.util.List;

public interface ChatroomService {

    List<ChatRoomDTO> getUserRooms(String userId);

    /**
     * This method creates a new chat room a return Id of chatRoom.
     * Also creates call over websocket
     * @param authorLogin
     * @param roomName
     * @param roomUsers
     * @return
     */
    String createNewRoom(String authorLogin, String roomName, List<String> roomUsers);

    void createGlobalChatRoom();

    PreparedChatRoomDto prepareRoom(String authorId, String userIdToChat);
}
