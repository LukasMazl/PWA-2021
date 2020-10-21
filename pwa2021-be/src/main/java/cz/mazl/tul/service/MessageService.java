package cz.mazl.tul.service;

import cz.mazl.tul.bussines.Message;

import java.util.List;

public interface MessageService {

    void saveMessage(Message message);

    List<Message> getAllMessageForRoom(String roomId);
}
