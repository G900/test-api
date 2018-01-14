# test-api

You should be able to start the example application by executing com.example.testapi.TestApiApplication, which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoint.

*The project is based on a small web service which uses the following technologies:

    * Java 1.8
    * Spring MVC with Spring Boot
    * Database H2 (In-Memory)
    * Maven

* The architecture of the web service is built with the following components:
   * Command: objects used to interact with API
   * Controller: Implements the processing logic of the web service, parsing of parameters.
   * Service: Implements the business logic and handles the access to the repositories.
   * Repositories: Interface for the database. Inserts, updates, deletes and reads objects from the database.
   * Entity: Functional Objects which might be persisted in the database.


