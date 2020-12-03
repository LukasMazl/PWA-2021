package cz.mazl.tul.bussines;

import java.util.Date;

public interface Message {
    Author getAuthor();
    String getMessage();
    String getTitle();
    Date getDate();
    boolean isMine();
}
