# test-api

You should be able to start the example application by executing com.example.testapi.TestApiApplication, which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoints.

* The project is based on a small web service which uses the following technologies:

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

* Application can show movies based on next parameters:
    * name - name of the movie.
    * genres - list of genres , supported values (ACTION,ADVENTURE,ANIMATION,BIOGRAPHY,COMEDY,CRIME,DOCUMENTARY,DRAMA,FAMILY,FANTASY,FILM_NOIR,HISTORY,HORROR,MUSIC,MUSICAL,MYSTERY,ROMANCE,SCI_FI,SHORT,SPORT,SUPERHERO,THRILLER,WAR,WESTERN)
    * year - supported values(1900-2099);
    * mpaaRating - supported values(G,PG,PG13,R,NC17);
    * director - name of direcor;
    * actors - name of actors;
    * name, director, actors  should contain "-" instead of space. Example: Tom-Hanks, steven-spielberg
    * if no parameters are provided, application returns list of all movies


