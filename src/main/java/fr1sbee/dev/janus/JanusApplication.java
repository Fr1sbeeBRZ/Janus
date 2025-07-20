package fr1sbee.dev.janus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //Turn on the audition
public class JanusApplication {

    public static void main(String[] args) {
        SpringApplication.run(JanusApplication.class, args);
    }

}
