package cz.mazl.tul.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chatroom")
public class ChatRoomEntity {

    @Id
    @Column(name = "room_id")
    @SequenceGenerator(name="pk_sequence",sequenceName="messagesounds_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    private long roomId;

    @Column(name = "chat_room_id")
    private String chatRoomId;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "chatrooms")
    private Set<UserEntity> userEntities = new HashSet<>();

    @OneToMany(mappedBy = "chatRoomEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MessageEntity> messageEntityList;


    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public List<MessageEntity> getMessageEntityList() {
        return messageEntityList;
    }

    public void setMessageEntityList(List<MessageEntity> messageEntityList) {
        this.messageEntityList = messageEntityList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
