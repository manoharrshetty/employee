package com.emp.config;


import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
@Configuration

public class AppConfig  {
	
	@Autowired
    private UserDetailsService userDetailsService;

  
    
  @Bean
  public DaoAuthenticationProvider authProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService);
      /*basically I am specifying that the BCryptPasswordEncoder(BCrypt algorithm) 
       * must be used to authenticate the user against the hashed password in the DB.
       * That is the user who will log in with plain text password will be hashed using 
       * this algorithm .This hashed password will then be compared against the one 
       * stored in the password column of user table.
       */
      authProvider.setPasswordEncoder(passwordEncoder());
      return authProvider;
  }
    /** BCryptPasswordEncoder does the hash along with salt
     * 
     * @return
     */
    @Bean
   	public PasswordEncoder passwordEncoder(){
   		PasswordEncoder encoder = new BCryptPasswordEncoder();
   		return encoder;
   	}
    
    /**
     * add etag header element for GET request.Can be used for reducing network bandwidth and for concurrency
     * @return
     */
    @Bean
    public Filter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }
    
    
   
}