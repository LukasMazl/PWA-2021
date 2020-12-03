package cz.mazl.tul.dto;

public class UserDto {
    private String userId;
    private String fullName;
    private String lastRoomId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastRoomId() {
        return lastRoomId;
    }

    public void setLastRoomId(String lastRoomId) {
        this.lastRoomId = lastRoomId;
    }

}
