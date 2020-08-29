package am.basic.notificator.controller;

import am.basic.notificator.config.SecurityContextProvider;
import am.basic.notificator.model.Notification;
import am.basic.notificator.model.User;
import am.basic.notificator.model.exceptions.AccessDeniedException;
import am.basic.notificator.model.exceptions.NotFoundException;
import am.basic.notificator.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SecurityContextProvider securityContextProvider;


    @PostMapping
    public ResponseEntity<Notification> add(@RequestBody Notification notification, OAuth2Authentication auth2Authentication) {
        User user = securityContextProvider.getByAuthentication(auth2Authentication);
        notification.setUserId(user.getId());
        notification.setEmail(user.getUsername());
        notification = notificationService.add(notification);
        return ResponseEntity.ok(notification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> update(@RequestBody Notification notification,
                                               @PathVariable long id,
                                               OAuth2Authentication auth2Authentication) throws NotFoundException, AccessDeniedException {
        User user = securityContextProvider.getByAuthentication(auth2Authentication);
        notification.setUserId(user.getId());
        notification = notificationService.update(notification, id);
        return ResponseEntity.ok(notification);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id, OAuth2Authentication auth2Authentication) throws NotFoundException {
        User user = securityContextProvider.getByAuthentication(auth2Authentication);
        notificationService.delete(user.getId(),id);
        return ResponseEntity.ok(id);
    }



    @GetMapping
    public ResponseEntity<List<Notification>> getAll(OAuth2Authentication auth2Authentication){
        User user = securityContextProvider.getByAuthentication(auth2Authentication);
        return ResponseEntity.ok(notificationService.getByUserId(user.getId()));
    }
}
