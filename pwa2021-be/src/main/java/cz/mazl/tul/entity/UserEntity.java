package cz.mazl.tul.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t01_user")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @SequenceGenerator(name="pk_sequence",sequenceName="messagesounds_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_email", unique = true, nullable = false)
    private String userId;

    @Column(name = "userAvatar", nullable = false)
    private String avatarUrl;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_chatroom",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "chatroom_id") }
    )
    private Set<ChatRoomEntity> chatrooms = new HashSet<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MessageEntity> messageEntityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<ChatRoomEntity> getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(Set<ChatRoomEntity> chatrooms) {
        this.chatrooms = chatrooms;
    }

    public List<MessageEntity> getMessageEntityList() {
        return messageEntityList;
    }

    public void setMessageEntityList(List<MessageEntity> messageEntityList) {
        this.messageEntityList = messageEntityList;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
