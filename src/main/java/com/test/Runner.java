package com.test;

import com.emp.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Runner {
    public static void main(String[] args) {


        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String admin = encoder.encode("admin");
        String support = encoder.encode("support");


        SpringApplication.run(Application.class, args);
    }
}
