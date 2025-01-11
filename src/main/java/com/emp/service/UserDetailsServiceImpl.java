package com.emp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emp.mapper.UsersMapper;
import com.emp.model.Users;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
   
   

    @Autowired
    private UsersMapper usersMapper;
	
    
    @Override
    public UserDetails loadUserByUsername(String name) {
        Users users = usersMapper.findByName(name);
        if (users == null) {
                 throw new UsernameNotFoundException("User not found by name: " + name);
        }
       
        return toUserDetails(users);
    }
    
    
    
    
    private UserDetails toUserDetails(Users user) {
  
    	return org.springframework.security.core.userdetails.User.withUsername(user.getName())
    			  .password(user.getPassword())
    			  //.password(user.getPassword())
                .roles(user.getRole()).build();
    	
    	
    	    }
    
        
}