package kz.yandex.notifications.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import kz.yandex.notifications.model.Notification;

public interface NotificationRepository extends R2dbcRepository<Notification, Long> {
    Flux<Notification> findAllByLogin(String login);
}
