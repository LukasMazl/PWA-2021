package cz.mazl.tul.dto;

public class PreparedChatRoomDto {

    private String roomId;

    public PreparedChatRoomDto(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "PreparedChatRoomDto{" +
                "roomId='" + roomId + '\'' +
                '}';
    }
}
