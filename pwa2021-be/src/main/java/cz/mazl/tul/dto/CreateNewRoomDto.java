package cz.mazl.tul.dto;

import java.util.List;

public class CreateNewRoomDto {

    private List<String> userList;
    private String roomName;

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "CreateNewRoomDto{" +
                "userList=" + userList +
                ", roomName='" + roomName + '\'' +
                '}';
    }
}
