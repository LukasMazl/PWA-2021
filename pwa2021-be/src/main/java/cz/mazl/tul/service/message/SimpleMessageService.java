package cz.mazl.tul.service.message;

import cz.mazl.tul.bussines.Author;
import cz.mazl.tul.bussines.Message;
import cz.mazl.tul.bussines.SimpleAuthor;
import cz.mazl.tul.bussines.SimpleMessage;
import cz.mazl.tul.config.props.DefaultChatRoomProperties;
import cz.mazl.tul.entity.ChatRoomEntity;
import cz.mazl.tul.entity.MessageEntity;
import cz.mazl.tul.entity.UserEntity;
import cz.mazl.tul.repository.ChatroomRepository;
import cz.mazl.tul.repository.MessageRepository;
import cz.mazl.tul.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class SimpleMessageService implements MessageService {

    private UserRepository userRepository;
    private MessageRepository messageRepository;
    private ChatroomRepository chatroomRepository;
    private DefaultChatRoomProperties defaultChatRoomProperties;

    @Autowired
    public SimpleMessageService(UserRepository userRepository, MessageRepository messageRepository,
                                ChatroomRepository chatroomRepository, DefaultChatRoomProperties defaultChatRoomProperties) {
        this.userRepository = userRepository;
        this.chatroomRepository = chatroomRepository;
        this.messageRepository = messageRepository;
        this.defaultChatRoomProperties = defaultChatRoomProperties;
    }

    @Override
    public void saveMessage(Message message, String roomId) {
        UserEntity userEntity = findUserEntity(message.getAuthor());
        ChatRoomEntity chatRoomEntity = findChatroom(userEntity, roomId);
        if(chatRoomEntity == null) {
            throw new RuntimeException("User can not access to " + roomId);
        }

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setAuthor(userEntity);
        messageEntity.setCreated(new Date());
        messageEntity.setChatRoomEntity(chatRoomEntity);
        messageEntity.setMessage(message.getMessage());

        messageRepository.save(messageEntity);
    }

    private ChatRoomEntity findChatroom(UserEntity userEntity, String roomId) {
        for(ChatRoomEntity chatRoomEntity : userEntity.getChatrooms()) {
            if(chatRoomEntity.getChatRoomId().compareTo(roomId) == 0)
                return chatRoomEntity;
        }
        return null;
    }

    private UserEntity findUserEntity(Author author) {
        return userRepository.findByUserId(author.getuserID());
    }

    @Override
    public List<Message> getAllMessageForRoom(String roomId, String userId) {
        ChatRoomEntity chatRoomEntity = chatroomRepository.findByChatRoomId(roomId);
        if(chatRoomEntity == null) {
            return new LinkedList<>();
        }

        List<MessageEntity> messageEntities = chatRoomEntity.getMessageEntityList();
        if (messageEntities == null) {
            return new LinkedList<>();
        }

        List<Message> messages = new LinkedList<>();
        for(MessageEntity messageEntity: messageEntities) {
            UserEntity author = messageEntity.getAuthor();
            SimpleMessage simpleMessage = new SimpleMessage();
            simpleMessage.setTitle(prepareChatRoomTitle(chatRoomEntity, userId));
            simpleMessage.setDate(messageEntity.getCreated());
            simpleMessage.setMine(messageEntity.getAuthor().getUserId().compareTo(userId) == 0);
            simpleMessage.setMessage(messageEntity.getMessage());
            SimpleAuthor simpleAuthor = new SimpleAuthor();
            simpleAuthor.setAuthorFullName(author.getName());
            simpleAuthor.setAvatar(author.getAvatarUrl());
            simpleAuthor.setUserId(author.getUserId());
            simpleMessage.setAuthor(simpleAuthor);
            messages.add(simpleMessage);
        }

        Collections.sort(messages);
        return messages;
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

    @Override
    public String getLastRoomId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) {
            throw new IllegalArgumentException(String.format("User %s does not exits.", userId));
        }
        List<MessageEntity> messageEntity = messageRepository.findByAuthorOrderByCreatedDesc(userEntity);
        if(messageEntity == null) {
            return defaultChatRoomProperties.getRoomId();
        }
        if(messageEntity.size() > 0) {
            return messageEntity.get(0).getChatRoomEntity().getChatRoomId();
        }
        return defaultChatRoomProperties.getRoomId();
    }
}
