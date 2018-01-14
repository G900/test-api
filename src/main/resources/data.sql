/**
 * CREATE Script for init of DB
 */

-- Create Actors
--God Father
insert into actor (id,name) values (1,'Marlon Brando');
insert into actor (id,name) values (2,'Al Pacino');
insert into actor (id,name) values (3,'James Caan');
insert into actor (id,name) values (4,'Diane Keaton');
--Shawshank Redemption
insert into actor (id,name) values (5,'Tim Robbins');
insert into actor (id,name) values (6,'Morgan Freeman');
insert into actor (id,name) values (7,'Bob Gunton');
insert into actor (id,name) values (8,'William Sadler');
--schindlers List
insert into actor (id,name) values (9,'Liam Nesson');
insert into actor (id,name) values (10,'Ralph Fiennes');
insert into actor (id,name) values (11,'Ben Kingsley');
insert into actor (id,name) values (12,'Caroline Goodall');
--psycho
insert into actor (id,name) values (13,'Anthony Perkins');
insert into actor (id,name) values (14,'Janet Leigh');
insert into actor (id,name) values (15,'Vera Miles');
insert into actor (id,name) values (16,'John Gavin');
--forrest gump
insert into actor (id,name) values (17,'Tom Hanks');
insert into actor (id,name) values (18,'Robin Wright');
insert into actor (id,name) values (19,'Gary Sinise');
insert into actor (id,name) values (20,'Sally Field');
--gladiator
insert into actor (id,name) values (21,'Russell Crowe');
insert into actor (id,name) values (22,'Joaquin Phoenix');
insert into actor (id,name) values (23,'Connie Nielsen');
insert into actor (id,name) values (24,'Oliver Reed');
--titanic
insert into actor (id,name) values (25,'Leonardo DiCaprio');
insert into actor (id,name) values (26,'Kate Winslet');
insert into actor (id,name) values (27,'Billy Zane');
insert into actor (id,name) values (28,'Kathy Bates');
--jaws
insert into actor (id,name) values (29,'Roy Scheider');
insert into actor (id,name) values (30,'Robert Shaw');
insert into actor (id,name) values (31,'Lorraine Gary');
insert into actor (id,name) values (32,'Richard Dreyfuss');
--green mile
insert into actor (id,name) values (33,'Michael Clarke Duncan');
insert into actor (id,name) values (34,'David Morse');
insert into actor (id,name) values (35,'Bonnie Hunt');

--Create Directors
-- god father
insert into director (id,name) values (1,'Francis Ford Coppola');
--Shawshank Redemption ,Green Mile
insert into director (id,name) values (2,'Frank Darabont');
--schindlers List ,jaws
insert into director (id,name) values (3,'Steven Spielberg');
--psycho
insert into director (id,name) values (4,'Alfred Hitchcock');
--forrest gump
insert into director (id,name) values (5,'Robert Zemeckis');
--gladiator
insert into director (id,name) values (6,'Ridle Scott');
--titanic
insert into director (id,name) values (7,'James Cameron');

--Create Movies
--the god father
insert into movie(id,name,year,director,duration,mpaa_rating,genres)
values (1,'The God Father',1972,1,175,'R','CRIME,DRAMA');
insert into movie_actors(movie_id,actors_id) values (1,1);
insert into movie_actors(movie_id,actors_id) values (1,2);
insert into movie_actors(movie_id,actors_id) values (1,3);
insert into movie_actors(movie_id,actors_id) values (1,4);

--Shawshank Redemption
insert into movie(id,name,year,director,duration,mpaa_rating,genres)
values (2,'The Shawshank Redemption',1994,2,142,'R','CRIME,DRAMA');
insert into movie_actors(movie_id,actors_id) values (2,5);
insert into movie_actors(movie_id,actors_id) values (2,6);
insert into movie_actors(movie_id,actors_id) values (2,7);
insert into movie_actors(movie_id,actors_id) values (2,8);
--schindlers List
insert into movie(id,name,year,director,duration,mpaa_rating,genres)
values (3,'Schindler''s List',1993,3,195,'R','BIOGRAPHY,HISTORY,DRAMA');
insert into movie_actors(movie_id,actors_id) values (3,9);
insert into movie_actors(movie_id,actors_id) values (3,10);
insert into movie_actors(movie_id,actors_id) values (3,11);
insert into movie_actors(movie_id,actors_id) values (3,12);
--psycho
insert into movie(id,name,year,director,duration,mpaa_rating,genres)
values (4,'Psycho',1960,4,109,'R','HORROR,MYSTERY,THRILLER');
insert into movie_actors(movie_id,actors_id) values (4,13);
insert into movie_actors(movie_id,actors_id) values (4,14);
insert into movie_actors(movie_id,actors_id) values (4,15);
insert into movie_actors(movie_id,actors_id) values (4,16);
--forrest gump
insert into movie(id,name,year,director,duration,mpaa_rating,genres)
values (5,'Forrest Gump',1994,5,142,'PG13','DRAMA,ROMANCE');
insert into movie_actors(movie_id,actors_id) values (5,17);
insert into movie_actors(movie_id,actors_id) values (5,18);
insert into movie_actors(movie_id,actors_id) values (5,19);
insert into movie_actors(movie_id,actors_id) values (5,20);
--gladiator
insert into movie(id,name,year,director,duration,mpaa_rating,genres)
values (6,'Gladiator',2000,6,155,'R','ACTION,ADVENTURE,DRAMA');
insert into movie_actors(movie_id,actors_id) values (6,21);
insert into movie_actors(movie_id,actors_id) values (6,22);
insert into movie_actors(movie_id,actors_id) values (6,23);
insert into movie_actors(movie_id,actors_id) values (6,24);
--titanic
insert into movie(id,name,year,director,duration,mpaa_rating,genres)
values (7,'Titanic',1997,7,194,'PG13','ROMANCE,DRAMA');
insert into movie_actors(movie_id,actors_id) values (7,25);
insert into movie_actors(movie_id,actors_id) values (7,26);
insert into movie_actors(movie_id,actors_id) values (7,27);
insert into movie_actors(movie_id,actors_id) values (7,28);
--jaws
insert into movie(id,name,year,director,duration,mpaa_rating,genres)
values (8,'Jaws',1975,3,124,'PG','ADVENTURE,DRAMA');
insert into movie_actors(movie_id,actors_id) values (8,29);
insert into movie_actors(movie_id,actors_id) values (8,30);
insert into movie_actors(movie_id,actors_id) values (8,31);
insert into movie_actors(movie_id,actors_id) values (8,32);
--green mile
insert into movie(id,name,year,director,duration,mpaa_rating,genres)
values (9,'The Green Mile',1999,2,189,'R','CRIME,FANTASY,DRAMA');
insert into movie_actors(movie_id,actors_id) values (9,17);
insert into movie_actors(movie_id,actors_id) values (9,34);
insert into movie_actors(movie_id,actors_id) values (9,35);
insert into movie_actors(movie_id,actors_id) values (9,33);