package kz.yandex.accounts.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import kz.yandex.accounts.model.User;

public interface UserRepository extends R2dbcRepository<User, Long> {
    Mono<User> findByLogin(String login);
}
