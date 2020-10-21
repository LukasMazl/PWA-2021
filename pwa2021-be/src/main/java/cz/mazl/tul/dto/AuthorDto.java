package cz.mazl.tul.dto;

import cz.mazl.tul.bussines.Author;

public class AuthorDto implements Author {
    private String authorFullName;
    private String userAvatar;

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
}
