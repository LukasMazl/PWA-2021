package cz.mazl.tul.dto;

import java.util.List;

public class HistoricalMessagesDTO {
    private String roomId;
    private boolean sorted;
    private List<SimpleMessageDTO> messages;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    public List<SimpleMessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<SimpleMessageDTO> messages) {
        this.messages = messages;
    }
}
