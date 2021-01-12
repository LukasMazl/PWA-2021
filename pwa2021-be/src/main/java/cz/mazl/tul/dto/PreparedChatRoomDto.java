package cz.mazl.tul.dto;

public class PreparedChatRoomDto {

    private String roomId;
    private String roomTitle;
    private HistoricalMessagesDTO historicalMessagesDTO;

    public PreparedChatRoomDto() {
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public HistoricalMessagesDTO getHistoricalMessagesDTO() {
        return historicalMessagesDTO;
    }

    public void setHistoricalMessagesDTO(HistoricalMessagesDTO historicalMessagesDTO) {
        this.historicalMessagesDTO = historicalMessagesDTO;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "PreparedChatRoomDto{" +
                "roomId='" + roomId + '\'' +
                ", roomTitle='" + roomTitle + '\'' +
                ", historicalMessagesDTO=" + historicalMessagesDTO +
                '}';
    }
}
