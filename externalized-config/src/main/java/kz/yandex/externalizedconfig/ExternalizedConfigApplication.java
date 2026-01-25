package kz.yandex.externalizedconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ExternalizedConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExternalizedConfigApplication.class, args);
    }
}
