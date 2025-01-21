package com.emp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig  {


    @Autowired
    private DaoAuthenticationProvider authProvider;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        note
        forbidden” (403) error.
        unauthorized” error (401).
         */



        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(HttpMethod.GET, "/employee/users/**").hasRole("ADMIN")//all request related to users must be an ADMIN
                                .requestMatchers(HttpMethod.GET, "/management/**").hasRole("ADMIN").//springboot actuator/application health
                        requestMatchers(HttpMethod.POST, "/employee/users").hasRole("ADMIN").
                        requestMatchers(HttpMethod.PUT, "/employee/users/**").hasRole("ADMIN").
                        requestMatchers(HttpMethod.DELETE, "/employee/users/**").hasRole("ADMIN").
                        requestMatchers(HttpMethod.GET, "/employee/emp/**").hasAnyRole("USER", "ADMIN")//all get request must be made by user has USER/ADMIN role configured in the user table.
                                .requestMatchers(HttpMethod.POST, "/employee/emp").hasAnyRole("USER", "ADMIN").
                        requestMatchers(HttpMethod.PUT, "/employee/emp/**").hasAnyRole("USER", "ADMIN").
                        requestMatchers(HttpMethod.PATCH, "/employee/emp/**").hasAnyRole("USER", "ADMIN").
                        requestMatchers(HttpMethod.DELETE, "/employee/emp/**").hasAnyRole("USER", "ADMIN").
                        requestMatchers(HttpMethod.GET, "/employee/dept/**").hasAnyRole("USER", "ADMIN")//all get request must be made by user has USER/ADMIN role configured in the user table.
                                .requestMatchers(HttpMethod.POST, "/employee/dept").hasAnyRole("USER", "ADMIN").
                        requestMatchers(HttpMethod.PUT, "/employee/dept/**").hasAnyRole("USER", "ADMIN").
                        requestMatchers(HttpMethod.PATCH, "/employee/dept/**").hasAnyRole("USER", "ADMIN").
                        requestMatchers(HttpMethod.DELETE, "/employee/dept/**").hasAnyRole("USER", "ADMIN").
                        requestMatchers("/employee/login/**").permitAll()//we’ll add a generic /login endpoint accessible by anyone, specific endpoints for admin and user, and an /all endpoint not secured by a role, but still requiring authentication.
                        .anyRequest().authenticated()

                )
                /*
                Configures the application to be stateless, which is typical for
                REST APIs where each request is independent and does not rely on session state.
                 */
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());

        return http.build();




    }






}