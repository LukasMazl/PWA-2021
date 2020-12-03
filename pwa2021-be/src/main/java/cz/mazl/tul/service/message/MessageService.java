package cz.mazl.tul.service.message;

import cz.mazl.tul.bussines.Message;

import java.util.List;

public interface MessageService {

    void saveMessage(Message message, String roomId);

    List<Message> getAllMessageForRoom(String roomId, String userId);

    String getLastRoomId(String userId);
}
