package am.basic.notificator.service.impl;

import am.basic.notificator.model.Notification;
import am.basic.notificator.model.exceptions.AccessDeniedException;
import am.basic.notificator.model.exceptions.NotFoundException;
import am.basic.notificator.repository.NotificationRepository;
import am.basic.notificator.service.NotificationService;
import am.basic.notificator.util.MailSenderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private MailSenderClient mailSenderClient;

    @Override
    public Notification add(Notification notification) {
        notification.setCreationDate(System.currentTimeMillis());
        return notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public Notification update(Notification notification, long id) throws NotFoundException, AccessDeniedException {
        Notification fromDb = notificationRepository
                .findByIdAndUserId(id, notification.getUserId())
                .orElseThrow(() -> new NotFoundException("wrong.notification.id"));

        AccessDeniedException.check(notification.isSent(), "notification.already.sent");

        fromDb.setTitle(notification.getTitle());
        fromDb.setContent(notification.getContent());
        fromDb.setDescription(notification.getDescription());
        fromDb.setNotificationDate(notification.getNotificationDate());
        fromDb.setSendEmail(notification.isSendEmail());
        fromDb.setSendPush(notification.isSendPush());
        fromDb.setSendSms(notification.isSendSms());

        return fromDb;
    }


    @Override
    public void delete(int userId, long id) throws NotFoundException {
        NotFoundException.check(!notificationRepository.existsByIdAndUserId(id, userId), "wrong.notification.id");
        notificationRepository.deleteById(id);
    }

    @Override
    public List<Notification> getByUserId(int userId) {
        return notificationRepository.getAllByUserId(userId);
    }

    @Override
    public List<Notification> getReadyNotifications() {
        return notificationRepository.getReadyNotifications(System.currentTimeMillis());
    }

    @Override
    @Async("notificationSenderExecutors")
    public void sendNotification(Notification notification) throws InterruptedException {
        long waitTime = notification.getNotificationDate() - System.currentTimeMillis();
        Thread.sleep(waitTime);
        mailSenderClient.sendSimpleMessage(notification.getEmail(), notification.getTitle(), notification.getContent());
        notification.setSent(true);
        notificationRepository.save(notification);
    }
}
