package cz.mazl.tul.dto;

import cz.mazl.tul.bussines.Message;

import java.util.List;

public class HistoricalMessagesDTO {
    private String roomId;
    private String roomTitle;
    private boolean sorted;
    private List<Message> messages;

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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }
}
