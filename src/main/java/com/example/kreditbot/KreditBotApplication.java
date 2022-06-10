package com.example.kreditbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KreditBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(KreditBotApplication.class, args);
    }

}
