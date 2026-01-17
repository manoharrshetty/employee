package com.emp.service;


import com.emp.model.User;
import com.emp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
   
   

    @Autowired
    private UserRepository userRepository;
	
    
    @Override
    public UserDetails loadUserByUsername(String userName) {
        List<User> users = userRepository.findByUserName(userName);
        if (users == null || users.size() != 1) {
                 throw new UsernameNotFoundException("User not found by name: " + userName);
        }
       
        return toUserDetails(users.get(0));
    }
    
    
    
    
    private UserDetails toUserDetails(User user) {
  
    	return org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
    			  .password(user.getUserPassword())
    			  //.password(user.getPassword())
                .roles(user.getUserRole()).build();
    	
    	
    	    }
    
        
}