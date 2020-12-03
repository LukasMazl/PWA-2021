package cz.mazl.tul.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @Column(name = "message_id")
    @SequenceGenerator(name="pk_sequence",sequenceName="messagesounds_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    private long id;

    @Column(name = "message")
    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private ChatRoomEntity chatRoomEntity;

    @Column(name = "created")
    private Date created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public ChatRoomEntity getChatRoomEntity() {
        return chatRoomEntity;
    }

    public void setChatRoomEntity(ChatRoomEntity chatRoomEntity) {
        this.chatRoomEntity = chatRoomEntity;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
