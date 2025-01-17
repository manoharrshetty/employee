package com.emp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author manoh
 * @EnableWebSecurity tells Spring Boot to drop its autoconfigured security policy and use this one instead.
 * For quick demos, autoconfigured security is okay. But for anything real, you should write the policy yourself.
 */
@EnableWebSecurity//enable Spring Security web security support and provide the Spring MVC integration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DaoAuthenticationProvider authProvider;

    /**
     * The configure(HttpSecurity) method defines which URL paths should be secured
     * and which should not.It also specifies the role
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //HTTP Basic authentication
                .httpBasic().and().authorizeRequests().
                antMatchers(HttpMethod.GET, "/employee/users/**").hasRole("ADMIN")//all request related to users must be an ADMIN
                .antMatchers(HttpMethod.GET, "/management/**").hasRole("ADMIN").
                antMatchers(HttpMethod.POST, "/employee/users").hasRole("ADMIN").
                antMatchers(HttpMethod.PUT, "/employee/users/**").hasRole("ADMIN").
                antMatchers(HttpMethod.DELETE, "/employee/users/**").hasRole("ADMIN").
                antMatchers(HttpMethod.GET, "/employee/emp/**").hasAnyRole("USER", "ADMIN")//all get request must be made by user has USER/ADMIN role configured in the user table.
                .antMatchers(HttpMethod.POST, "/employee/emp").hasAnyRole("USER", "ADMIN").
                antMatchers(HttpMethod.PUT, "/employee/emp/**").hasAnyRole("USER", "ADMIN").
                antMatchers(HttpMethod.PATCH, "/employee/emp/**").hasAnyRole("USER", "ADMIN").
                antMatchers(HttpMethod.DELETE, "/employee/emp/**").hasAnyRole("USER", "ADMIN").
                antMatchers(HttpMethod.GET, "/employee/dept/**").hasAnyRole("USER", "ADMIN")//all get request must be made by user has USER/ADMIN role configured in the user table.
                .antMatchers(HttpMethod.POST, "/employee/dept").hasAnyRole("USER", "ADMIN").
                antMatchers(HttpMethod.PUT, "/employee/dept/**").hasAnyRole("USER", "ADMIN").
                antMatchers(HttpMethod.PATCH, "/employee/dept/**").hasAnyRole("USER", "ADMIN").
                antMatchers(HttpMethod.DELETE, "/employee/dept/**").hasAnyRole("USER", "ADMIN").and()
                /*disable Cross-Site Request Forgery (CSRF)
                 * BASIC login is also configured with CSRF disabled. This is mostly for demonstrations and not recommended for production systems without careful analysis.
                 */
                .csrf().disable().formLogin().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }


}