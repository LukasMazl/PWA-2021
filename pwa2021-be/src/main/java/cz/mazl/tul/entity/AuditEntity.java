package cz.mazl.tul.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audit")
public class AuditEntity {

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="messagesounds_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name = "audit_id")
    private long id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "sessionId")
    private String sessionId;

    @Column(name = "created")
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
