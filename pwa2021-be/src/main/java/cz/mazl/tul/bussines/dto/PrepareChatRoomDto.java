package cz.mazl.tul.bussines.dto;

public class PrepareChatRoomDto {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PreparedChatRoomDto{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
