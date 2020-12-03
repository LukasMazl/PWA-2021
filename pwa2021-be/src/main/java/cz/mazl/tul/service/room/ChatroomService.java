package cz.mazl.tul.service.room;

import cz.mazl.tul.dto.ChatRoomDTO;

import java.util.List;

public interface ChatroomService {

    List<ChatRoomDTO> getUserRooms(String userId);

    /**
     * This method creates a new chat room a return Id of chatRoom.
     * Also creates call over websocket
     * @param authorLogin
     * @param roomUsers
     * @return
     */
    String createNewRoom(String authorLogin, List<String> roomUsers);

    void createGlobalChatRoom();
}
