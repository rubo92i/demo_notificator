package am.basic.notificator.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int userId;

    private String title;

    private String content;

    private String description;

    private long notificationDate;

    private long creationDate;

    private boolean sent;

    private boolean sendSms;

    private boolean sendEmail;

    private boolean sendPush;

    private String email;

}
