package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class RestApiWebBanHangSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiWebBanHangSpringBootApplication.class, args);
        System.out.println("hello web");
    }

}

// todo tao history table