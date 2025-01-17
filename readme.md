Key features :

-Combined Spring Security  and HTTPS
End-to-End Security: Spring Security handles a) user authentication b) user authorization, while c) HTTPS ensures the communication
channel is secure (wire level/transmission security).
HTTPS helps meet industry security standards like GDPR, HIPAA, and PCI DSS.Spring security protects against common
attacks like:
Cross-Site Request Forgery (CSRF)
Cross-Site Scripting (XSS)
Session fixation
By implementing HTTPS and Spring Security together, our application achieves a high level of security for both data in
transit and application-level access control.

My implementation of Spring Security helps address several issues from the OWASP Top Ten.Here’s how Spring Security aligns with some of the OWASP Top Ten vulnerabilities:
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



-For our employee SpringBoot REST application we use the widely recommended BCrypt hashing algorithm (one way
encryption).
It is a widely used password hashing function designed for secure storage.
It is particularly favored for its ability to include a salt, its computational cost factor, and its resistance to
brute-force attacks. In Spring Security, BCrypt is implemented via the BCryptPasswordEncoder class.

-We use MyBatis,which is a lightweight persistence framework in Java that simplifies database interaction by automating
the mapping
between SQL queries and Java objects. It allows you to write SQL directly and map results to Java objects using XML or
annotations. This makes MyBatis a powerful tool for managing database operations with fine-grained control over queries.

--If you use the spring-boot-starter-jdbc or spring-boot-starter or 
spring-boot-starter-data-jpa starters, you automatically get a dependency to HikariCP.
We prefer HikariCP for its performance and concurrency. If HikariCP is available, we always choose it.
Supported Connection Pools
Spring Boot uses the following algorithm for choosing a specific implementation:
We prefer HikariCP for its performance and concurrency. If HikariCP is available, we always choose it.
Otherwise, if the Tomcat pooling DataSource is available, we use it.
Otherwise, if Commons DBCP2 is available, we use it.
If none of HikariCP, Tomcat, and DBCP2 are available and if Oracle UCP is available, we use it.

-Enabled spring boot actuator
In essence, Actuator brings production-ready features to our application.
Monitoring our app, gathering metrics, and understanding traffic or the state of our database becomes trivial with this dependency.
The main benefit of this library is that we can get production-grade tools without actually having to implement these features ourselves.
The actuator mainly exposes operational information about the running application — health, metrics, info, dump, env, etc. It uses HTTP endpoints or JMX beans to enable us to interact with it.
e.g. try https://localhost:8443/management/metrics/hikaricp.connections will display total mysql connections in the pool.
Health metrics include heap memory used,total idle connections in the connection pool,garbage  collection pauses,process cpu usage and many more


-We use the Spring Framework’s declarative transaction management (@Transactional annotation) , that is  made possible with 
Spring aspect-oriented programming (AOP).This means you will not see rollback,commit ,getConnection,releaseConnection anywhere in our app.Spring
transaction management takes care of this boiler plate code.
-Implemented AspectJ, a powerful framework for implementing Aspect-Oriented Programming (AOP) in Java.
We also used AspectJ to log bad performing sql and bad performing Rest Endpoint,without going through the tedious process of requesting the DBA or
production support resources.
e.g.   class -> EmployeeAspect
@Around("execution(* java.sql.PreparedStatement.execute* (..))")
public Object aroundPreparedStatementExecute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
Instant start = null;



I used it to log the start time and end time of each Spring Boot REST API calls effectively. Also used to log error.All
these was achieved without
touching the actual business code.Key benefits :
Centralized Logging: Logs all API calls in one place without modifying individual controllers.
Performance Tracking: Measures the time taken for each API call.
Error Handling: Logs exceptions with minimal boilerplate.
This approach makes the application more maintainable and provides valuable insights into our API's runtime behavior.


-In this spring boot application we are using ehcache to cache the employee information.
This helps to improve performance.
EHCache offers efficient in-memory caching to boost application performance by reducing database load. Key features
include support for distributed caching, cache eviction policies, and integration with Spring for seamless
configuration.
EHCache not only improves performance through in-memory caching but also handles cache consistency automatically.
Whenever an update, insert, or delete operation occurs, EHCache ensures the cache is updated, preventing stale data from
being served.
e.g. logging details after enabling debug log.The below log indicates 50% usage of the cache to retrieve employee after
2nd get postman request.
2025-01-13 14:50:24,618 DEBUG org.apache.ibatis.cache.decorators.LoggingCache [https-jsse-nio-8443-exec-9] Cache Hit
Ratio [com.emp.mapper.EmpMapper]: 0.5

set up git
-create the new project in intellij.Create the employee project in intellij as gradle project.
-initialize git in the project.go to VCS -> enable version control integration and select git
-go to your github profile -> settings -> developer settings -> personal access token -> tokens classic -> generate new
token classic
-create a new github repo.In github create a repo without the readme.md and .gitignore file
-add the github remote to your intellij project.Git -> manage remote -> + -> git repo url -> ok
-add and commit your project.vcs -> git -> add.vcs -> commit
-push your code to github.select your branch -> token if prompted.
