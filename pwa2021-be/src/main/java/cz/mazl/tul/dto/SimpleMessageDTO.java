package cz.mazl.tul.dto;

import cz.mazl.tul.bussines.Author;
import cz.mazl.tul.bussines.Message;

import java.util.Date;

public class SimpleMessageDTO implements Message {

    private String message;
    private Author author;
    private Date date;
    private boolean mine;

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

    public void setDate(Date data) {
        this.date = data;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }
}
