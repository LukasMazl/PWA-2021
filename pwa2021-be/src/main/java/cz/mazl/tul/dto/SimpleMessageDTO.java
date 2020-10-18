package cz.mazl.tul.dto;

import java.util.Date;

public class SimpleMessageDTO {

    private String message;
    private AuthorDto author;
    private Date date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date data) {
        this.date = data;
    }
}
