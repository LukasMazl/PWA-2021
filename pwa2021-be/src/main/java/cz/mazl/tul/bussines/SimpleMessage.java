package cz.mazl.tul.bussines;

import java.util.Date;

public class SimpleMessage implements Message {

    private String message;
    private Author author;
    private Date date;

    public SimpleMessage(){
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
