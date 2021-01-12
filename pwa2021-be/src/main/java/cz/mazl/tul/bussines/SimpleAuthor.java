package cz.mazl.tul.bussines;

public class SimpleAuthor implements Author {

    private String authorFullName;
    private String avatar;
    private String userId;

    public SimpleAuthor(){
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getAuthorFullName() {
        return authorFullName;
    }

    @Override
    public String getUserAvatar() {
        return avatar;
    }

    @Override
    public String getuserID() {
        return userId;
    }
}
