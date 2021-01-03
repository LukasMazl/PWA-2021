package cz.mazl.tul.service.room;

import cz.mazl.tul.config.props.DefaultChatRoomProperties;
import cz.mazl.tul.dto.ChatRoomDTO;
import cz.mazl.tul.dto.PreparedChatRoomDto;
import cz.mazl.tul.entity.ChatRoomEntity;
import cz.mazl.tul.entity.UserEntity;
import cz.mazl.tul.repository.ChatroomRepository;
import cz.mazl.tul.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SimpleChatRoomService implements ChatroomService {

    private DefaultChatRoomProperties defaultChatRoomProperties;
    private ChatroomRepository chatroomRepository;
    private UserRepository userRepository;

    @Autowired
    public SimpleChatRoomService(ChatroomRepository chatroomRepository,
                                 DefaultChatRoomProperties defaultChatRoomProperties,
                                 UserRepository userRepository) {
        this.chatroomRepository = chatroomRepository;
        this.defaultChatRoomProperties = defaultChatRoomProperties;
        this.userRepository = userRepository;
    }

    @Override
    public List<ChatRoomDTO> getUserRooms(String userId) {
        return null;
    }

    @Transactional
    @Override
    public String createNewRoom(String authorLogin, String roomName, List<String> roomUsers) {
        List<UserEntity> userEntities = userRepository.findAllByUserIdIn(roomUsers);
        UserEntity authorEntity = userRepository.findByUserId(authorLogin);

        String roomId = UUID.randomUUID().toString();

        Set<UserEntity> roomUsersEntities = new HashSet<>();
        roomUsersEntities.addAll(userEntities);
        roomUsersEntities.add(authorEntity);

        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setTitle(roomName);
        chatRoomEntity.setChatRoomId(roomId);
        chatRoomEntity.setUserEntities(roomUsersEntities);

        chatroomRepository.save(chatRoomEntity);

        for(UserEntity entity: roomUsersEntities) {
            entity.getChatrooms().add(chatRoomEntity);
            userRepository.save(authorEntity);
        }

        return roomId;
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

    @Override
    public PreparedChatRoomDto prepareRoom(String authorId, String userIdToChat) {
        UserEntity authorEntity = userRepository.findByUserId(authorId);

        Set<ChatRoomEntity> authorChatRoomEntities = authorEntity.getChatrooms();
        for(ChatRoomEntity chatRoomEntity : authorChatRoomEntities) {
            if(chatRoomEntity.getChatRoomId().compareTo(defaultChatRoomProperties.getRoomId()) != 0
                    && chatRoomEntity.getUserEntities().size() == 2) {
                for(UserEntity userInRomm : chatRoomEntity.getUserEntities()) {
                    if(userInRomm.getUserId().compareTo(userIdToChat) == 0)
                    return new PreparedChatRoomDto(chatRoomEntity.getChatRoomId());
                }
            }
        }

        return new PreparedChatRoomDto(createNewRoom(authorId, "", Arrays.asList(userIdToChat)));
    }
}
