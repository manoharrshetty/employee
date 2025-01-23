# Summary
This is an Enterprise ready Employee application with critical CRUD operations exposed as a Spring based REST API.
The purpose of the app is to show how more can be achieved with less code.The design encourages developers
to only focus on the business logic and leave the rest to the framework.Non-business functionality such as
security(authentication,authorization,wire level encryption) ,logging,transaction management,health and performance 
monitoring,caching etc. are completely handled by framework with little or no code.AI was used to get personal employee 
insights.
Extensive use of generics helped in reuse of code,ensuring type safety and improve code readability
Technology stack I am using is Spring Boot,Spring Boot Actuator, Spring Transaction Management,
Spring Security, MySQL, AspectJ, Spring AI, Ehcache and MyBatis


# AI and Spring AI
AI and Spring AI was incorporated into the application to  
significantly enhance the functionality and efficiency of an employee maintenance REST API.
Example usage is to get a Personalized Employee Insights .AI can generate personalized insights based on employee data.In the case
of this application,we use OpenAI to recommend training for employee based on their present skills and their career goal.
e.g. com/emp/ai/AiServiceImpl.java

# Enterprise Integration patterns

Here’s a concise summary of the enterprise patterns I am  following by 
using Spring Boot, Spring Security, AspectJ, Spring AI, Ehcache, and MyBatis:

1. Dependency Injection (DI) / IoC: Spring Boot manages object creation and dependency injection, promoting loose coupling.
Model-View-Controller (MVC): Spring Boot organizes the application into presentation, business logic, and data access layers.
2. Data Access Object (DAO): MyBatis abstracts database interactions, isolating persistence logic from business logic.
3. Aspect-Oriented Programming (AOP): AspectJ and Spring separate cross-cutting concerns like logging, security, or transaction management.
4. Caching Pattern: Ehcache optimizes performance by temporarily storing frequently accessed data in memory.
5. Proxy Pattern: Spring Security uses proxies for access control, while Ehcache applies it for caching mechanisms.
6. Observer Pattern: Spring Boot Actuator and events allow monitoring and notifying subscribers of changes or metrics.
7. Template Method Pattern: MyBatis simplifies database queries using predefined templates for CRUD operations.
8. Factory Pattern: Spring AI dynamically creates and configures components like machine learning models or services.
9. Authentication and Authorization Patterns: Spring Security implements role-based access control and secure session management.
10. Transaction Management: Spring manages transactions declaratively, ensuring consistency and integrity.
11. Layered Architecture: Your application is structured into presentation, business, and persistence layers for modularity.
These patterns collectively enable scalability, modularity, and maintainability of our REST API. 

# Application Security
In the REST API I have combined Spring Security  and HTTPS
End-to-End Security: Spring Security handles 
1. user authentication 
2. user authorization, while 
3. HTTPS ensures the communication channel is secure (wire level/transmission security).
HTTPS helps meet industry security standards like GDPR, HIPAA, and PCI DSS.

Spring security protects against common
attacks like:
- Cross-Site Request Forgery (CSRF)
- Cross-Site Scripting (XSS)
- Session fixation.
- By implementing HTTPS and Spring Security together, our application achieves a high level of security for both data in
transit and application-level access control.

My implementation of Spring Security helps address several issues from the OWASP Top Ten.Here’s how my implementation of Spring Security aligns with some of the OWASP Top Ten vulnerabilities:
	
1.	Broken Access Control :Spring Security provides role-based and method-level security to enforce access controls. 
2.	Cryptographic Failures:Spring Security handles password storage with strong encryption (e.g., BCrypt) and ensures secure protocols like HTTPS 
3.	Injection :Spring Security doesn’t directly handle SQL injection but integrating with frameworks like MyBatis, which can prevent it using parameterized queries.
4.	Insecure Design:While not a design tool, Spring Security encourages secure coding practices and defaults to secure settings, helping reduce design flaws.
5.	Security Misconfiguration :Spring Boot and Security default to secure configurations, like CSRF protection, secure cookies, and default headers (e.g., X-Content-Type-Options, X-XSS-Protection).
6.	Vulnerable and Outdated Components :Managing dependencies via tools like Maven or Gradle helps avoid outdated libraries, 
        though Spring Security itself won’t prevent you from introducing vulnerable components.
7.	Identification and Authentication Failures : Spring Security provides built-in authentication mechanisms like form login, OAuth2, and session management to reduce risks.
8.	Software and Data Integrity Failures :Spring Security supports features like content security policies (CSPs) and secure headers to mitigate integrity issues.
9.	Security Logging and Monitoring Failures : While Spring Security doesn’t include logging out of the box, integrating it with tools like Spring Boot Actuator or external monitoring systems can help.
10.	Server-Side Request Forgery (SSRF)  :Spring Security doesn’t directly handle SSRF, but you can mitigate it by validating and sanitizing user input, controlling outbound requests, and blocking unnecessary network access.

e.g. com/emp/config/WebSecurityConfig.java

# BCrypt hashing algorithm
For our employee SpringBoot REST application we use the widely recommended BCrypt hashing algorithm (one way
encryption).
It is a widely used password hashing function designed for secure storage.
It is particularly favored for its ability to include a salt, its computational cost factor, and its resistance to
brute-force attacks. In Spring Security, BCrypt is implemented via the BCryptPasswordEncoder class.
e.g com/emp/config/AppConfig.java -> passwordEncoder() -> PasswordEncoder encoder = new BCryptPasswordEncoder();


# MyBatis
We use MyBatis,which is a lightweight persistence framework in Java that simplifies database interaction by automating
the mapping
between SQL queries and Java objects. It allows you to write SQL directly and map results to Java objects using XML or
annotations. This makes MyBatis a powerful tool for managing database operations with fine-grained control over queries.
MyBatis improves performance by providing first level caching by default .Second Level caching is also available.Unfortunately
it is not very robust and enterprise ready.Hence we have used EHCache for second level caching.
If you use the spring-boot-starter-jdbc or spring-boot-starter or 
spring-boot-starter-data-jpa starters, you automatically get a dependency to HikariCP.
We prefer HikariCP for its performance and concurrency. If HikariCP is available, we always choose it.
Supported Connection Pools
Spring Boot uses the following algorithm for choosing a specific implementation:
We prefer HikariCP for its performance and concurrency. If HikariCP is available, we always choose it.
Otherwise, if the Tomcat pooling DataSource is available, we use it.
Otherwise, if Commons DBCP2 is available, we use it.
If none of HikariCP, Tomcat, and DBCP2 are available and if Oracle UCP is available, we use it.
e.g. main/resources/myBatis/EmpMapper.xml

# Application Monitoring
Enabled spring boot actuator
In essence, Actuator brings production-ready features to our application.
Monitoring our app, gathering metrics, and understanding traffic or the state of our database becomes trivial with this dependency.
The main benefit of this library is that we can get production-grade tools without actually having to implement these features ourselves.
The actuator mainly exposes operational information about the running application — health, metrics, info, dump, env, etc. It uses HTTP endpoints or JMX beans to enable us to interact with it.
e.g. try https://localhost:8443/management/metrics/hikaricp.connections will display total mysql connections in the pool.
Health metrics include heap memory used,total idle connections in the connection pool,garbage  collection pauses,process cpu usage and many more

e.g. src/main/resources/application.properties -> management.endpoints.web.exposure.include=health,info,metrics 


# declarative transaction management (@Transactional annotation) 
We use the Spring Framework’s declarative transaction management (@Transactional annotation) , that is  made possible with
Spring aspect-oriented programming (AOP).This means you will not see rollback,commit ,getConnection,releaseConnection anywhere in our app.Spring
transaction management takes care of this boiler plate code.
e.g. com/emp/service/EmpServiceImpl.java


# AspectJ and AOP
Implemented AspectJ, a powerful framework for implementing Aspect-Oriented Programming (AOP) in Java.
We also used AspectJ to log bad performing sql and bad performing Rest Endpoint,without going through the tedious process of requesting the DBA or
production support resources.
e.g.   com/aspectj/db/EmployeeAspect.java

I used it to log the start time and end time of each Spring Boot REST API calls effectively. Also used to log error.All
these was achieved without
touching the actual business code.Key benefits :
Centralized Logging: Logs all API calls in one place without modifying individual controllers.
Performance Tracking: Measures the time taken for each API call.
Error Handling: Logs exceptions with minimal boilerplate.
This approach makes the application more maintainable and provides valuable insights into our API's runtime behavior.


# Caching
In this spring boot application we are using EHCache to cache the employee information.
This helps to improve performance.
EHCache offers efficient in-memory caching to boost application performance by reducing database load. Key features
include support for distributed caching, cache eviction policies, and integration with Spring for seamless
configuration.
EHCache not only improves performance through in-memory caching but also handles cache consistency automatically.
Whenever an update, insert, or delete operation occurs, EHCache ensures the cache is updated, preventing stale data from
being served.
e.g. src/main/resources/myBatis/EmpMapper.xml
logging details after enabling debug log.The below log indicates 50% usage of the cache to retrieve employee after
2nd get postman request.
2025-01-13 14:50:24,618 DEBUG org.apache.ibatis.cache.decorators.LoggingCache [https-jsse-nio-8443-exec-9] Cache Hit
Ratio [com.emp.mapper.EmpMapper]: 0.5


# Next Step (TODO)
Containerized (Dockerize) the spring boot based REST API application so that it can be moved to Azure infrastructure
using lift-and-shift architecture.This will ensure that the app is scalable.

