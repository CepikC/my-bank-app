package kz.yandex.frontui.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import kz.yandex.dto.account.UserDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {
    private final WebClient accountClient;
    @Value("${accounts.base-url}")
    private String accountsBaseUrl;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return accountClient
                .get()
                .uri(accountsBaseUrl + "/auth/users/{username}", username)
                .retrieve()
                .bodyToMono(UserDto.class)
                .map(this::mapToUserDetails);
    }

    private UserDetails mapToUserDetails(UserDto userDto) {
        return new org.springframework.security.core.userdetails.User(
                userDto.getLogin(),
                userDto.getPassword(),
                List.of(new SimpleGrantedAuthority(userDto.getRole())));
    }
}
