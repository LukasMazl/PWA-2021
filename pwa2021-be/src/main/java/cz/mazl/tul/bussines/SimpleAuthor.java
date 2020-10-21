package cz.mazl.tul.bussines;

public class SimpleAuthor implements Author {

    private String authorFullname;
    private String avatar;

    public SimpleAuthor(){
    }

    public void setFullname(String authorFullname) {
        this.authorFullname = authorFullname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String getAuthorFullName() {
        return authorFullname;
    }

    @Override
    public String getUserAvatar() {
        return avatar;
    }
}
