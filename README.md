# Vivlio

βιβλίο (Reading: Vivlio) in Greek means book. This is just simple Book management API. The goal of this project is to implementing HATEOAS architecture, authentication with JWT using OAuth2 Resource Server and method based authorization using built-in Spring Security. And also implement custom UserDetails and its service that can be persisted into database.

## Installation

Requirement:
- Java 21 or later
- PostgreSQL database server

Steps:
1. Migrate the database from file `database.sql` that already located in the root directory of this repository.
2. Generate private and public RSA key (you can use tool like [openssl](https://www.openssl.org/) to generate it). Feel free to locate those file anywhere in you machine but i prefer put them in project classpath (inside `src/main/resources` folder).
3. Configure the app. You can follow the example from `application.yaml.example` file inside `src/main/resources`.
4. Run the app: `./mvnw spring-boot:run`
