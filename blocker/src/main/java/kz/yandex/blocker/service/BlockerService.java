package kz.yandex.blocker.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class BlockerService {


    public Mono<Boolean> isBlocked(LocalDateTime now) {
        return Mono.just(false);
    }
}
