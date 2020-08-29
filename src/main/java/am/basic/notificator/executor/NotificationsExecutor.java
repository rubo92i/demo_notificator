package am.basic.notificator.executor;


import am.basic.notificator.model.Notification;
import am.basic.notificator.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationsExecutor {

    @Autowired
    private NotificationService notificationService;


    @Scheduled(fixedRate = 30 * 1000)
    public void start() throws InterruptedException {
        List<Notification> notifications = notificationService.getReadyNotifications();
        for (Notification notification : notifications) {
            notificationService.sendNotification(notification);
        }
    }

}
