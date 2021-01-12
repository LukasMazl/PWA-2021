package cz.mazl.tul.service.room;

import cz.mazl.tul.bussines.Message;
import cz.mazl.tul.bussines.SimpleMessage;
import cz.mazl.tul.config.props.DefaultChatRoomProperties;
import cz.mazl.tul.dto.ChatRoomDTO;
import cz.mazl.tul.dto.HistoricalMessagesDTO;
import cz.mazl.tul.dto.PreparedChatRoomDto;
import cz.mazl.tul.entity.ChatRoomEntity;
import cz.mazl.tul.entity.MessageEntity;
import cz.mazl.tul.entity.UserEntity;
import cz.mazl.tul.repository.ChatroomRepository;
import cz.mazl.tul.repository.UserRepository;
import cz.mazl.tul.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SimpleChatRoomService implements ChatroomService {

    private DefaultChatRoomProperties defaultChatRoomProperties;
    private ChatroomRepository chatroomRepository;
    private UserRepository userRepository;
    private MessageService messageService;

    @Autowired
    public SimpleChatRoomService(ChatroomRepository chatroomRepository,
                                 DefaultChatRoomProperties defaultChatRoomProperties,
                                 UserRepository userRepository,
                                 MessageService messageService) {
        this.chatroomRepository = chatroomRepository;
        this.defaultChatRoomProperties = defaultChatRoomProperties;
        this.userRepository = userRepository;
        this.messageService = messageService;
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
        UserEntity userForChat = userRepository.findByUserId(userIdToChat);
        if(userForChat == null) {
            throw new IllegalArgumentException("User " + userIdToChat + " for chatting does not exist.");
        }

        Set<ChatRoomEntity> authorChatRoomEntities = authorEntity.getChatrooms();
        for(ChatRoomEntity chatRoomEntity : authorChatRoomEntities) {
            if(chatRoomEntity.getChatRoomId().compareTo(defaultChatRoomProperties.getRoomId()) != 0
                    && chatRoomEntity.getUserEntities().size() == 2) {
                for(UserEntity userInRomm : chatRoomEntity.getUserEntities()) {
                    if(userInRomm.getUserId().compareTo(userIdToChat) == 0) {
                        return prepareRoomDto(chatRoomEntity, authorId);
                    }
                }
            }
        }

        String roomId = createNewRoom(authorId, "", Arrays.asList(userIdToChat));
        PreparedChatRoomDto chatRoomDto = new PreparedChatRoomDto();
        chatRoomDto.setRoomId(roomId);
        chatRoomDto.setRoomTitle(userForChat.getName());
        HistoricalMessagesDTO historicalMessagesDTO = new HistoricalMessagesDTO();
        historicalMessagesDTO.setSorted(true);
        historicalMessagesDTO.setRoomTitle(userForChat.getName());
        historicalMessagesDTO.setRoomId(roomId);
        historicalMessagesDTO.setMessages(new LinkedList<>());
        chatRoomDto.setHistoricalMessagesDTO(historicalMessagesDTO);

        return chatRoomDto;
    }

    private PreparedChatRoomDto prepareRoomDto(ChatRoomEntity chatRoomEntity, String authorId) {
        PreparedChatRoomDto preparedChatRoomDto = new PreparedChatRoomDto();
        preparedChatRoomDto.setRoomId(chatRoomEntity.getChatRoomId());
        preparedChatRoomDto.setRoomTitle(prepareChatRoomTitle(chatRoomEntity, authorId));
        preparedChatRoomDto.setHistoricalMessagesDTO(convertToHistoricalMessage(chatRoomEntity, authorId));
        return preparedChatRoomDto;
    }

    private String prepareChatRoomTitle(ChatRoomEntity chatRoomEntity, String userId) {
        StringBuilder titleStringBuilder = new StringBuilder();
        for(UserEntity userEntity: chatRoomEntity.getUserEntities()) {
            if(userEntity.getUserId().compareTo(userId) != 0) {
                titleStringBuilder.append(userEntity.getName());
                titleStringBuilder.append(", ");
            }
        }
        return titleStringBuilder.substring(0, titleStringBuilder.length() - 2);
    }

    private HistoricalMessagesDTO convertToHistoricalMessage(ChatRoomEntity chatRoomEntity, String authorId) {
        HistoricalMessagesDTO historicalMessagesDTO = new HistoricalMessagesDTO();
        historicalMessagesDTO.setSorted(true);
        historicalMessagesDTO.setRoomId(chatRoomEntity.getChatRoomId());
        historicalMessagesDTO.setMessages(messageService.getAllMessageForRoom(chatRoomEntity.getChatRoomId(),authorId));
        return historicalMessagesDTO;
    }

}
