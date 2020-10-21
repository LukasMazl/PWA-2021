package cz.mazl.tul.bussines;

import java.util.Date;

public interface Message {
    Author getAuthor();
    String getMessage();
    Date getDate();
}
