package com.bit.ww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WwApplication {

    public static void main(String[] args) {
        SpringApplication.run(WwApplication.class, args);
    }

}
