package cz.mazl.tul.service;

import cz.mazl.tul.bussines.Message;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Profile(value = "mock")
public class MockMessageService implements MessageService {

    private static List<Message> messages = new LinkedList<>();

    @Override
    public void saveMessage(Message message) {
        messages.add(message);
    }

    @Override
    public List<Message> getAllMessageForRoom(String roomId) {
        return messages;
    }
}
