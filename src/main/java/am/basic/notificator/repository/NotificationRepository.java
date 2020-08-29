package am.basic.notificator.repository;

import am.basic.notificator.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByIdAndUserId(long id, int userId);

    boolean existsByIdAndUserId(long id, int userId);

    List<Notification> getAllByUserId(int userId);

    @Query(nativeQuery = true, value = "SELECT * FROM notification WHERE sent = false AND (notification_date BETWEEN :time AND  (:time + 30000))")
    List<Notification> getReadyNotifications(@Param("time") long time);
}
