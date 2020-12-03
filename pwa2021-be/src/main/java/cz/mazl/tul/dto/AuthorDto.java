package cz.mazl.tul.dto;

import cz.mazl.tul.bussines.Author;

public class AuthorDto implements Author {

    private String authorFullName;
    private String userAvatar;
    private String userId;

    @Override
    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    @Override
    public String getUserAvatar() {
        return userAvatar;
    }

    public void setAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    @Override
    public String getuserID() {
        return userId;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
