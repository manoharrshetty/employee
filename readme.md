# Summary

This is an Enterprise ready Employee application with critical CRUD operations exposed as a Spring based REST API.
The purpose of the app is to show how more can be achieved with less code.The design encourages developers
to only focus on the business logic and leave the rest to the framework.Non-business functionality such as
security(authentication,authorization,wire level encryption) ,logging,transaction management,health and performance
monitoring,caching etc. are completely handled by framework with little or no code.AI was used to get personal employee
insights.
Extensive use of established enterprise patterns and generics helped in reuse of code,ensuring type safety and improve
code readability
Technology stack and protocol, I am using is Spring Boot,Spring Boot Actuator, Spring Transaction Management,
Spring Security, MySQL, AspectJ, Spring AI, Redis , Spring Data,OAuth 2.0,OpenID Connect,GCP Identity Management.

# AI and Spring AI

AI and Spring AI was incorporated into the application to  
significantly enhance the functionality and efficiency of an employee maintenance REST API without locking itself to any
one vendor.
Example usage is to get a Personalized Employee Insights .AI can generate personalized insights based on employee
data.In the case of this application,I used OpenAI to recommend training for employee based on their present skills and
their career
goal. Outputs are produced as structured Java DTOs via prompt templates; DTOs are validated (Jakarta Bean Validation)
and verified by an LLM verifier that returns a verdict and confidence score.
In future will use enhance the code for evaluating the generated content to ensure the AI model has not produced a
hallucinated response.
One method to evaluate the response is to use the AI model itself for evaluation. Select the best AI model for the
evaluation, which may not be the same model used to generate the response.
The Spring AI interface for evaluating responses is Evaluator.

I have also used RAP (Retrieval-Augmented Prompting) .It is a pattern that improves LLM outputs by retrieving relevant enterprise context and injecting it into prompts before calling the model. It reduces hallucination and increases factuality and relevance.

Example

1. com/emp/ai/AiServiceImpl.java

# Portability & Vendor-Agnostic Design

This application is built with a vendor-agnostic and portable architecture at its core, ensuring flexibility and
independence from specific cloud providers or infrastructure setups. The key technologies used in this stack enable
seamless migration and deployment across various environments, including on-prem, private cloud, and public cloud
platforms.

1. Spring Boot provides a containerized, cross-platform environment, allowing the application to run consistently in any
   environment (e.g., Docker, Kubernetes) without any vendor-specific dependencies.
2. Spring Boot Actuator offers built-in health checks and metrics without tying the application to a specific cloud or
   vendor, ensuring it remains cloud-agnostic.
3. Prometheus is used for monitoring and metrics collection, providing flexibility to run on any infrastructure without
   relying on proprietary cloud solutions.
4. Spring Data and Redis (via Spring Data Redis) are data-source agnostic, allowing the application to integrate with
   any database or caching solution and avoiding dependency on a specific vendor. Spring Data provides repository-driven,
   portable data access (JPA, Spring Data JDBC, etc.),
   while Redis—configured through Spring Cache and Spring Data Redis—offers a distributed, high-performance cache for
   scalable caching.
5. Spring Security and Spring Transaction are configured in a modular, flexible way, ensuring the application’s security
   and transaction management work across different systems and environments, without relying on vendor-specific
   security or transaction handling mechanisms.
   This project uses Google OAuth 2.0 / OpenID Connect (OIDC) for authentication.
   Postman acts as the OAuth client and obtains tokens from Google (IdP/Authorization Server).
   The Spring Boot REST API acts as a Resource Server, validating the JWT access token on each request.
   In future versions endpoints will be protected using scopes/claims (and optional role/ownership checks) for authorization.


6. Spring AI is integrated using structured output, prompt templates, and an LLM-based verification pipeline. Outputs
   are emitted as typed Java objects (POJOs/DTOs) rather than raw JSON and are validated with standard Java validation (
   e.g., Jakarta Bean Validation) and schema checks. Future version will have Verification is performed by invoking an LLM verifier which
   evaluates correctness, consistency.
7. AspectJ and Spring AOP (Aspect-Oriented Programming) allow for the modularization of cross-cutting concerns such as
   logging, transaction management, and security, without hard-coding these concerns into business logic. This enables
   the application to maintain its flexibility and portability, allowing for easier maintenance and extension across
   multiple environments, free from vendor-specific constraints.

By leveraging these technologies in a modular and cloud-agnostic way, the application ensures easy migration and
deployment in various cloud environments or on-prem setups, providing long-term scalability and flexibility without the
risk of vendor lock-in.

# Enterprise Integration patterns

Here’s a concise summary of the enterprise patterns I am following by
using Spring Boot, Spring Security, AspectJ, Spring AI, Ehcache, and MyBatis:

1. Dependency Injection (DI) / IoC: Spring Boot manages object creation and dependency injection, promoting loose
   coupling.
   Model-View-Controller (MVC): Spring Boot organizes the application into presentation, business logic, and data access
   layers.
2. Data Access Object (DAO): Spring Data abstracts database interactions, isolating persistence logic from business logic.
3. Aspect-Oriented Programming (AOP): AspectJ and Spring separate cross-cutting concerns like logging, security, or
   transaction management.
4. Caching Pattern: Redis optimizes performance by temporarily storing frequently accessed data in memory.
5. Proxy Pattern: Spring Security uses proxies for access control, while Spring Redis applies it for caching mechanisms.
6. Observer Pattern: Spring Boot Actuator and events allow monitoring and notifying subscribers of changes or metrics.
7. Template Method Pattern: Spring Data simplifies database queries using predefined templates for CRUD operations.
8. Factory Pattern: Spring AI dynamically creates and configures components like machine learning models or services.
9. Authentication and Authorization Patterns: Spring Security implements role-based access control and secure session
   management.
10. Transaction Management: Spring manages transactions declaratively, ensuring consistency and integrity.
11. Layered Architecture: Your application is structured into presentation, business, and persistence layers for
    modularity.
    These patterns collectively enable scalability, modularity, and maintainability of our REST API.

# Application Security

In the REST API I have combined Spring Security and HTTPS
End-to-End Security: Spring Security handles

1. user authentication
2. user authorization, while
3. HTTPS ensures the communication channel is secure (wire level/transmission security).
   For now it is one way ssl where the spring boot server proves its identity to the client.This means only the Spring
   Boot server presents its SSL certificate to the client during HTTPS communication. The client verifies the server’s
   identity, but the client does not provide its own certificate. This setup is called one-way SSL or one-way TLS, and
   it secures data in transit by authenticating the server to the client

HTTPS helps meet industry security standards like GDPR, HIPAA, and PCI DSS.

Spring security protects against common
attacks like:

- Cross-Site Request Forgery (CSRF)
- Cross-Site Scripting (XSS)
- Session fixation.
- By implementing HTTPS and Spring Security together, our application achieves a high level of security for both data in
  transit and application-level access control.

My implementation of Spring Security helps address several issues from the OWASP Top Ten.Here’s how my implementation of
Spring Security aligns with some of the OWASP Top Ten vulnerabilities:

1. Broken Access Control :Spring Security provides role-based and method-level security to enforce access controls.
2. Cryptographic Failures:Spring Security handles password storage with strong encryption (e.g., BCrypt) and ensures
   secure protocols like HTTPS
3. Injection :Spring Security doesn’t directly handle SQL injection but integrating with frameworks like MyBatis, which
   can prevent it using parameterized queries.
4. Insecure Design:While not a design tool, Spring Security encourages secure coding practices and defaults to secure
   settings, helping reduce design flaws.
5. Security Misconfiguration :Spring Boot and Security default to secure configurations, like CSRF protection, secure
   cookies, and default headers (e.g., X-Content-Type-Options, X-XSS-Protection).
6. Vulnerable and Outdated Components :Managing dependencies via tools like Maven or Gradle helps avoid outdated
   libraries,
   though Spring Security itself won’t prevent you from introducing vulnerable components.
7. Identification and Authentication Failures : Spring Security provides built-in authentication mechanisms like form
   login, OAuth2, and session management to reduce risks.
8. Software and Data Integrity Failures :Spring Security supports features like content security policies (CSPs) and
   secure headers to mitigate integrity issues.
9. Security Logging and Monitoring Failures : While Spring Security doesn’t include logging out of the box, integrating
   it with tools like Spring Boot Actuator or external monitoring systems can help.
10. Server-Side Request Forgery (SSRF)  :Spring Security doesn’t directly handle SSRF, but you can mitigate it by
    validating and sanitizing user input, controlling outbound requests, and blocking unnecessary network access.

Example

1. com/emp/config/WebSecurityConfig.java

# BCrypt hashing algorithm

For our employee SpringBoot REST application I have used the widely recommended BCrypt hashing algorithm (one way
encryption).
It is a widely used password hashing function designed for secure storage.
It is particularly favored for its ability to include a salt, its computational cost factor, and its resistance to
brute-force attacks. In Spring Security, BCrypt is implemented via the BCryptPasswordEncoder class.

Example

1. com/emp/config/AppConfig.java -> passwordEncoder() -> PasswordEncoder encoder = new BCryptPasswordEncoder();

# MyBatis

We use MyBatis,which is a lightweight persistence framework in Java that simplifies database interaction by automating
the mapping
between SQL queries and Java objects. It allows you to write SQL directly and map results to Java objects using XML or
annotations. This makes MyBatis a powerful tool for managing database operations with fine-grained control over queries.
MyBatis improves performance by providing first level caching by default .Second Level caching is also
available.Unfortunately
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

Example

1. main/resources/myBatis/EmpMapper.xml

# Monitoring & Health Checks

This project leverages Spring Boot Actuator and Prometheus for robust, cost-effective monitoring with minimal
configuration and coding.

1. Spring Boot Actuator: Automatically exposes essential features like health checks, metrics, and environment
   information with
   little setup, reducing the need for additional code. It provides built-in endpoints for monitoring the status of
   components like the database and disk space. Free and open-source, it simplifies the monitoring process without
   additional costs.

2. Prometheus: Collects and stores application metrics (e.g., JVM health, request counts, response times) with minimal
   setup.
   Once integrated, Prometheus automatically scrapes the metrics, making it easy to get detailed insights with almost no
   extra coding effort. Free and open-source, it enables powerful monitoring with low overhead.

3. Grafana Integration (will accommodate this in future version ) : Easily integrates with Prometheus to visualize
   metrics in Grafana dashboards, requiring little to no
   custom code. It helps monitor trends and identify issues proactively with free and open-source tools.

4. Proactive Monitoring: Alerts can be configured quickly to notify you of critical issues (e.g., high memory usage or
   error rates)
   with minimal setup. The combination of Spring Boot Actuator, Prometheus, and Grafana ensures your application is
   continuously monitored with little effort and no additional costs.

By using these tools, we can achieve comprehensive monitoring and proactive issue resolution with minimal work, while
keeping costs at zero with free, open-source solutions.

Enabled spring boot actuator
In essence, Actuator brings production-ready features to our application.
Monitoring our app, gathering metrics, and understanding traffic or the state of our database becomes trivial with this
dependency.
The main benefit of this library is that we can get production-grade tools without actually having to implement these
features ourselves.
The actuator mainly exposes operational information about the running application — health, metrics, info, dump, env,
etc. It uses HTTP endpoints or JMX beans to enable us to interact with it.
e.g. try
https://localhost:8443/management/metrics/hikaricp.connections will display total mysql connections in the
pool.
https://localhost:8443/management/metrics/vm.memory.used will shows the current amount of JVM memory used, measured in
bytes.

Health metrics include heap memory used,total idle connections in the connection pool,garbage collection pauses,process
cpu usage and many more

Example

1. src/main/resources/application.properties -> management.endpoints.web.exposure.include=health,info,metrics

# Declarative transaction management

We use the Spring Framework’s declarative transaction management (@Transactional annotation) , that is made possible
with
Spring aspect-oriented programming (AOP).This means you will not see rollback,commit ,getConnection,releaseConnection
anywhere in our app.Spring
transaction management takes care of this boiler plate code.

Example

1. com/emp/service/EmpServiceImpl.java

# AspectJ and AOP

Implemented AspectJ, a powerful framework for implementing Aspect-Oriented Programming (AOP) in Java.
I also used AspectJ to log bad performing sql and bad performing Rest Endpoint,without going through the tedious process
of requesting the DBA or
production support resources.

Example

1. com/aspectj/db/EmployeeAspect.java

I used it to log the start time and end time of each Spring Boot REST API calls effectively. Also used to log error.All
these was achieved without
touching the actual business code.Key benefits :
Centralized Logging: Logs all API calls in one place without modifying individual controllers.
Performance Tracking: Measures the time taken for each API call.
Error Handling: Logs exceptions with minimal boilerplate.
This approach makes the application more maintainable and provides valuable insights into our API's runtime behavior.

# Caching

In this spring boot application I used EHCache to cache the employee information.
This helps to improve performance.
EHCache offers efficient in-memory caching to boost application performance by reducing database load. Key features
include support for distributed caching, cache eviction policies, and integration with Spring for seamless
configuration.
EHCache not only improves performance through in-memory caching but also handles cache consistency automatically.
Whenever an update, insert, or delete operation occurs, EHCache ensures the cache is updated, preventing stale data from
being served.

Example

1. src/main/resources/myBatis/EmpMapper.xml
2. logging details after enabling debug log.The below log indicates 50% usage of the cache to retrieve employee after
   2nd get postman request.
   2025-01-13 14:50:24,618 DEBUG org.apache.ibatis.cache.decorators.LoggingCache [https-jsse-nio-8443-exec-9] Cache Hit
   Ratio [com.emp.mapper.EmpMapper]: 0.5

# Next Step (TODO)

Replace Basic Authentication with OAuth2 for improved security and scalability. Implement OAuth2 integration using
Spring Security to provide token-based authentication, ensuring better management of user sessions and secure access
across multiple services. This will help decouple authentication from the application, making it more adaptable for
cloud-native and microservice architectures.
