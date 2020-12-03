package cz.mazl.tul.bussines;

public class SimpleAuthor implements Author {

    private String authorFullname;
    private String avatar;
    private String userId;

    public SimpleAuthor(){
    }

    public void setFullname(String authorFullname) {
        this.authorFullname = authorFullname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAuthorFullname() {
        return authorFullname;
    }

    public void setAuthorFullname(String authorFullname) {
        this.authorFullname = authorFullname;
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
        return authorFullname;
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
