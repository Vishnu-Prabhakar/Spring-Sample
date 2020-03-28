This is a skeleton project for spring boot with 
- REST API
- MySql DB and spring data JPA
- Spring scheduler
- Spring AOP for logging and exception handling with contoller advice
- Swagger integration
- Spring profiles

To run the application,
Import the project in eclipse via File-> Import -> type existing maven projects in the search -> browse the project -> click Finish
To run the project from eclipse: Right click on the SpringbootmysqlApplication.java and run as java application or 
from the source folder execute the command: mvn spring-boot:run
To access the rest service for the application use any rest client and hit, http://localhost:8080/employee

This application connects to mysql database.

for spring derived method names:
 Refer: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details 
 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
