package com.test;

import com.emp.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Runner {
    public static void main(String[] args) {


        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("admin");

        SpringApplication.run(Application.class, args);
    }
}
