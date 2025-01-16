Key features :

-Combined Spring Security and HTTPS
End-to-End Security: Spring Security handles user authentication/authorization, while HTTPS ensures the communication
channel is secure.
HTTPS helps meet industry security standards like GDPR, HIPAA, and PCI DSS.Spring security protects against common
attacks like:
Cross-Site Request Forgery (CSRF)
Cross-Site Scripting (XSS)
Session fixation
By implementing HTTPS and Spring Security together, our application achieves a high level of security for both data in
transit and
application-level access control.

-For our employee SpringBoot REST application we use the widely recommended BCrypt hashing algorithm (one way
encryption).
It is a widely used password hashing function designed for secure storage.
It is particularly favored for its ability to include a salt, its computational cost factor, and its resistance to
brute-force attacks. In Spring Security, BCrypt is implemented via the BCryptPasswordEncoder class.

-We use MyBatis,which is a lightweight persistence framework in Java that simplifies database interaction by automating
the mapping
between SQL queries and Java objects. It allows you to write SQL directly and map results to Java objects using XML or
annotations. This makes MyBatis a powerful tool for managing database operations with fine-grained control over queries.

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
