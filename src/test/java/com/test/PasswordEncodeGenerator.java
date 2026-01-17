package com.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncodeGenerator {

  public static void main(String[] args) {

		  

		String password = "K!delq6w";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);



  }
  
  

}