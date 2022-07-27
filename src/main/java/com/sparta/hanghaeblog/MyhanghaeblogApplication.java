package com.sparta.hanghaeblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyhanghaeblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyhanghaeblogApplication.class, args);
    }

}
