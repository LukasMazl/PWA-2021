package cz.mazl.tul.service.room;

import cz.mazl.tul.config.props.DefaultChatRoomProperties;
import cz.mazl.tul.dto.ChatRoomDTO;
import cz.mazl.tul.entity.ChatRoomEntity;
import cz.mazl.tul.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleChatRoomService implements ChatroomService {

    private DefaultChatRoomProperties defaultChatRoomProperties;
    private ChatroomRepository chatroomRepository;

    @Autowired
    public SimpleChatRoomService(ChatroomRepository chatroomRepository, DefaultChatRoomProperties defaultChatRoomProperties) {
        this.chatroomRepository = chatroomRepository;
        this.defaultChatRoomProperties = defaultChatRoomProperties;
    }

    @Override
    public List<ChatRoomDTO> getUserRooms(String userId) {
        return null;
    }

    @Override
    public String createNewRoom(String authorLogin, List<String> roomUsers) {
        return null;
    }

    @Override
    public void createGlobalChatRoom() {
        ChatRoomEntity chatRoomEntity = chatroomRepository.findByChatRoomId(defaultChatRoomProperties.getRoomId());
        if(chatRoomEntity != null) {
            return;
        }

        chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setChatRoomId(defaultChatRoomProperties.getRoomId());
        chatRoomEntity.setTitle(defaultChatRoomProperties.getRoomName());
        chatroomRepository.save(chatRoomEntity);
    }
}
