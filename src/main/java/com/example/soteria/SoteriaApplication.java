package com.example.soteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.soteria")
public class SoteriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoteriaApplication.class, args);
    }
}