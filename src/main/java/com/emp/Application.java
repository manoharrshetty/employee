package com.emp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * 
 * Under the hood, @SpringBootApplication is a convenience annotation that adds
 * all of the following:
 * 
 * @Configuration tags the class as a source of bean definitions for the
 *                application context.
 * 
 * @EnableAutoConfiguration tells Spring Boot to start adding beans based on
 *                          classpath settings, other beans, and various
 *                          property settings. For example, if spring-webmvc is
 *                          on the classpath this flags the application as a web
 *                          application and activates key behaviors such as
 *                          setting up a DispatcherServlet.
 * 
 * @ComponentScan tells Spring to look for other components, configurations, and
 *                services starting from the current package com.emp allowing it
 *                to find the controllers.
 * 
 *                With this default setting, Spring Boot will auto scan for
 *                components in the current package (containing the @SpringBoot
 *                main class) and its sub packages.
 * @author manoh
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
