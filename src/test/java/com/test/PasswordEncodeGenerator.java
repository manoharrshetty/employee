package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncodeGenerator {

  public static void main(String[] args) {
	  try {
		  
		  
		  
		  
		  
		  List<String> users = new ArrayList<String>();
		  users.add("1");
		  users.add("2");
		  String r = users.stream().reduce("",String::concat);
		  System.out.println(r);
		  
		  
		  
		  int a = 10/0;
		  //int a = Math.addExact(Integer.MAX_VALUE , Integer.MAX_VALUE);
	  }catch(ArrayIndexOutOfBoundsException e) {
		  e.printStackTrace();
	  }
	int i = 0;
	while (i < 10) {
		String password = "K!delq6w";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);
		i++;
	}

  }
  
  
  public static void show(Supplier<String> name) {
	  System.out.println("welcome:" + name .get());
  }
}