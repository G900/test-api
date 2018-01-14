package com.example.testapi;

import com.example.testapi.command.GetMoviesByParametersCommand;
import com.example.testapi.entity.ActorEntity;
import com.example.testapi.entity.DirectorEntity;
import com.example.testapi.entity.MovieEntity;
import com.example.testapi.exception.ValidationFailureException;
import com.example.testapi.helper.Genre;
import com.example.testapi.helper.MpaaRating;
import com.example.testapi.repository.ActorRepository;
import com.example.testapi.repository.DirectorRepository;
import com.example.testapi.repository.MovieRepository;
import com.example.testapi.result.MovieResult;
import com.example.testapi.service.MovieService;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created By G900 on 13-Jan-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestConfig.class)
@Transactional
public class MovieServiceTest {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieService movieService;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    DirectorRepository directorRepository;
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void insert() {
        prepareData();
    }

    @After
    public void cleanUp() {

        actorRepository.deleteAll();
        directorRepository.deleteAll();
        movieRepository.deleteAll();
    }

    @Test
    public void should_throw_validation_errors() throws Exception {
        thrown.expect(ValidationFailureException.class);
        GetMoviesByParametersCommand command1 = new GetMoviesByParametersCommand();
        command1.setGenres(Collections.singletonList("Pizza"));
        command1.setMpaaRating("Burger");
        command1.setYear("5001");
        AtomicInteger count = new AtomicInteger();
        try {
            movieService.getMoviesByParameters(command1);
        } catch (ValidationFailureException e) {
            Assert.assertEquals(4, e.getErrors().size());
            e.getErrors().stream().forEach(x -> {
                if (x.getMessage().equals("Not Enum Value") && x.getProperty().equals("genre")) {
                    count.incrementAndGet();
                }
                if (x.getMessage().equals("Not Enum Value") && x.getProperty().equals("mpaaRating")) {
                    count.incrementAndGet();
                }
                if (x.getMessage().equals("Year should be in range 1900-2099") && x.getProperty().equals("year")) {
                    count.incrementAndGet();
                }
                if (x.getMessage().equals("Invalid get movies command")) {
                    count.incrementAndGet();
                }
            });
            Assert.assertTrue(count.intValue() == 4);
            throw e;
        }
    }

    @Test
    public void should_get_list_of_movies_by_genres() throws Exception {
        GetMoviesByParametersCommand command1 = new GetMoviesByParametersCommand();

        command1.setGenres(Collections.singletonList("Drama"));
        List<MovieResult> dramaMovies = movieService.getMoviesByParameters(command1);
        Assert.assertEquals(8, dramaMovies.size());

        GetMoviesByParametersCommand command2 = new GetMoviesByParametersCommand();
        command2.setGenres(Arrays.asList("Crime", "Drama"));
        List<MovieResult> dramaCrimeMovies = movieService.getMoviesByParameters(command2);
        Assert.assertEquals(3, dramaCrimeMovies.size());

        GetMoviesByParametersCommand command3 = new GetMoviesByParametersCommand();
        command3.setGenres(Arrays.asList("crime", "DRAMA", "fanTasy"));
        List<MovieResult> dramaCrimeFantasyMovies = movieService.getMoviesByParameters(command3);
        Assert.assertEquals(1, dramaCrimeFantasyMovies.size());

    }

    @Test
    public void should_get_list_of_movies_by_actors() throws Exception {
        GetMoviesByParametersCommand command1 = new GetMoviesByParametersCommand();

        command1.setActors(Collections.singletonList("Tom Hanks"));
        List<MovieResult> tomHanksMovies = movieService.getMoviesByParameters(command1);
        Assert.assertEquals(2, tomHanksMovies.size());

        GetMoviesByParametersCommand command2 = new GetMoviesByParametersCommand();
        command2.setActors(Arrays.asList("Tom Hanks", "David Morse"));
        List<MovieResult> tomHanksAndDavidMorseMovies = movieService.getMoviesByParameters(command2);
        Assert.assertEquals(1, tomHanksAndDavidMorseMovies.size());

    }


    @Test
    public void should_get_list_of_movies_by_mpaaRating() throws Exception {
        GetMoviesByParametersCommand command1 = new GetMoviesByParametersCommand();

        command1.setMpaaRating("r");
        List<MovieResult> rMovies = movieService.getMoviesByParameters(command1);
        Assert.assertEquals(6, rMovies.size());

        GetMoviesByParametersCommand command2 = new GetMoviesByParametersCommand();
        command2.setMpaaRating("pG13");
        List<MovieResult> pg13Movies = movieService.getMoviesByParameters(command2);
        Assert.assertEquals(2, pg13Movies.size());

        GetMoviesByParametersCommand command3 = new GetMoviesByParametersCommand();
        command3.setMpaaRating("PG");
        List<MovieResult> pgMovies = movieService.getMoviesByParameters(command3);
        Assert.assertEquals(1, pgMovies.size());

    }

    @Test
    public void should_get_list_of_movies_by_year() throws Exception {
        GetMoviesByParametersCommand command1 = new GetMoviesByParametersCommand();
        command1.setYear("1994");
        List<MovieResult> movies1994 = movieService.getMoviesByParameters(command1);
        Assert.assertEquals(2, movies1994.size());
    }

    @Test
    public void should_get_list_of_movies_by_director() throws Exception {
        GetMoviesByParametersCommand command1 = new GetMoviesByParametersCommand();
        command1.setDirector("steven spielberg");
        List<MovieResult> spielbergMovies = movieService.getMoviesByParameters(command1);
        Assert.assertEquals(2, spielbergMovies.size());

        GetMoviesByParametersCommand command2 = new GetMoviesByParametersCommand();
        command2.setDirector("JaMeS CaMeRon");
        List<MovieResult> jameCameronMovies = movieService.getMoviesByParameters(command2);
        Assert.assertEquals(1, jameCameronMovies.size());
    }

    @Test
    public void should_get_list_of_movies_by_name() throws Exception {
        GetMoviesByParametersCommand command1 = new GetMoviesByParametersCommand();
        command1.setName("green mile");
        List<MovieResult> greenMileMovie = movieService.getMoviesByParameters(command1);
        Assert.assertEquals(1, greenMileMovie.size());

    }
    @Test
    public void should_get_empty_list() throws Exception {

        GetMoviesByParametersCommand command1 = new GetMoviesByParametersCommand();
        command1.setName("avatar");
        List<MovieResult> movies = movieService.getMoviesByParameters(command1);
        Assert.assertEquals(0, movies.size());

        GetMoviesByParametersCommand command2 = new GetMoviesByParametersCommand();
        command2.setDirector("frank darabont");
        command2.setGenres(Arrays.asList("Crime", "Drama"));
        command2.setMpaaRating("R");
        command2.setActors(Collections.singletonList("George Clooney"));

        List<MovieResult> movies2 = movieService.getMoviesByParameters(command1);
        Assert.assertEquals(0, movies2.size());


    }

    @Test
    public void should_get_list_of_movies_by_various_parameters() throws Exception {
        GetMoviesByParametersCommand command1 = new GetMoviesByParametersCommand();
        command1.setDirector("frank darabont");
        command1.setGenres(Arrays.asList("Crime", "Drama"));
        command1.setMpaaRating("R");


        List<MovieResult> movieList1 = movieService.getMoviesByParameters(command1);
        Assert.assertEquals(2, movieList1.size());
        AtomicInteger count = new AtomicInteger();
        movieList1.stream().forEach(x -> {
            if (x.getName() == "The Green Mile" || x.getName() == "The Shawshank Redemption") {
                count.incrementAndGet();
            }
        });
        Assert.assertTrue(count.intValue() == 2);


        command1.setActors(Collections.singletonList("Tom Hanks"));

        List<MovieResult> movieList2 = movieService.getMoviesByParameters(command1);
        Assert.assertEquals(1, movieList2.size());
        Assert.assertEquals("The Green Mile", movieList2.get(0).getName());

    }


    public void prepareData() {

        ActorEntity tomHanks = new ActorEntity("Tom Hanks");

        List<ActorEntity> theGodFatherActorList = generateAndSaveActorList(new ActorEntity("Marlon Brando"),
                new ActorEntity("Al Pacino"),
                new ActorEntity("James Caan"),
                new ActorEntity("Diane Keaton"));

        DirectorEntity francisFordCoppola = new DirectorEntity("Francis Ford Coppola");
        directorRepository.save(francisFordCoppola);


        MovieEntity theGodfatherEntity = new MovieEntity();
        theGodfatherEntity.setName("The Godfather");
        theGodfatherEntity.setYear("1972");
        theGodfatherEntity.setDirector(francisFordCoppola);
        theGodfatherEntity.setDuration(175L);
        theGodfatherEntity.setMpaaRating(MpaaRating.R);
        theGodfatherEntity.setActors(theGodFatherActorList);
        theGodfatherEntity.setGenres(String.format("%s, %s", Genre.CRIME.name(), Genre.DRAMA.name()));
        movieRepository.save(theGodfatherEntity);

        List<ActorEntity> theShawshankRedemptionActorList = generateAndSaveActorList(new ActorEntity("Tim Robbins"),
                new ActorEntity("Morgan Freeman"),
                new ActorEntity("Bob Gunton"),
                new ActorEntity("William Sadler"));

        DirectorEntity frankDarabont = new DirectorEntity("Frank Darabont");
        directorRepository.save(frankDarabont);

        MovieEntity theShawshankRedemptionEntity = new MovieEntity();
        theShawshankRedemptionEntity.setName("The Shawshank Redemption");
        theShawshankRedemptionEntity.setYear("1994");
        theShawshankRedemptionEntity.setDirector(frankDarabont);
        theShawshankRedemptionEntity.setDuration(142L);
        theShawshankRedemptionEntity.setMpaaRating(MpaaRating.R);
        theShawshankRedemptionEntity.setActors(theShawshankRedemptionActorList);
        theShawshankRedemptionEntity.setGenres(String.format("%s, %s", Genre.CRIME.name(), Genre.DRAMA.name()));
        movieRepository.save(theShawshankRedemptionEntity);

        List<ActorEntity> schindlersListActorList = generateAndSaveActorList(new ActorEntity("Liam Nesson"),
                new ActorEntity("Ralph Fiennes"),
                new ActorEntity("Ben Kingsley"),
                new ActorEntity("Caroline Goodall"));
        DirectorEntity stevenSpielberg = new DirectorEntity("Steven Spielberg");
        directorRepository.save(stevenSpielberg);

        MovieEntity schindlersListEntity = new MovieEntity();
        schindlersListEntity.setName("Schindler's List");
        schindlersListEntity.setYear("1993");
        schindlersListEntity.setDirector(stevenSpielberg);
        schindlersListEntity.setDuration(195L);
        schindlersListEntity.setMpaaRating(MpaaRating.R);
        schindlersListEntity.setActors(schindlersListActorList);
        schindlersListEntity.setGenres(String.format("%s, %s, %s", Genre.BIOGRAPHY.name(), Genre.DRAMA.name(), Genre.HISTORY.name()));
        movieRepository.save(schindlersListEntity);

        List<ActorEntity> psychoActorList = generateAndSaveActorList(new ActorEntity("Anthony Perkins"),
                new ActorEntity("Janet Leigh"),
                new ActorEntity("Vera Miles"),
                new ActorEntity("John Gavin"));

        DirectorEntity alfredHitchcock = new DirectorEntity("Alfred Hitchcock");
        directorRepository.save(alfredHitchcock);

        MovieEntity psychoEntity = new MovieEntity();
        psychoEntity.setName("Psycho");
        psychoEntity.setYear("1960");
        psychoEntity.setDirector(alfredHitchcock);
        psychoEntity.setDuration(109L);
        psychoEntity.setMpaaRating(MpaaRating.R);
        psychoEntity.setActors(psychoActorList);
        psychoEntity.setGenres(String.format("%s, %s, %s", Genre.HORROR.name(), Genre.MYSTERY.name(), Genre.THRILLER.name()));
        movieRepository.save(psychoEntity);


        List<ActorEntity> forrestGumpActorList = generateAndSaveActorList(tomHanks,
                new ActorEntity("Robin Wright"),
                new ActorEntity("Gary Sinise"),
                new ActorEntity("Sally Field"));

        DirectorEntity robertZemeckis = new DirectorEntity("Robert Zemeckis");
        directorRepository.save(robertZemeckis);

        MovieEntity forrestGumpEntity = new MovieEntity();
        forrestGumpEntity.setName("Forrest Gump");
        forrestGumpEntity.setYear("1994");
        forrestGumpEntity.setDirector(robertZemeckis);
        forrestGumpEntity.setDuration(142L);
        forrestGumpEntity.setMpaaRating(MpaaRating.PG13);
        forrestGumpEntity.setActors(forrestGumpActorList);
        forrestGumpEntity.setGenres(String.format("%s, %s", Genre.DRAMA.name(), Genre.ROMANCE.name()));
        movieRepository.save(forrestGumpEntity);


        List<ActorEntity> gladiatorActorList = generateAndSaveActorList(new ActorEntity("Russell Crowe"),
                new ActorEntity("Joaquin Phoenix"),
                new ActorEntity("Connie Nielsen"),
                new ActorEntity("Oliver Reed"));

        DirectorEntity ridleScott = new DirectorEntity("Ridle Scott");
        directorRepository.save(ridleScott);

        MovieEntity gladiatorEntity = new MovieEntity();
        gladiatorEntity.setName("Gladiator");
        gladiatorEntity.setYear("2000");
        gladiatorEntity.setDirector(ridleScott);
        gladiatorEntity.setDuration(155L);
        gladiatorEntity.setMpaaRating(MpaaRating.R);
        gladiatorEntity.setActors(gladiatorActorList);
        gladiatorEntity.setGenres(String.format("%s, %s, %s", Genre.ACTION.name(), Genre.ADVENTURE.name(), Genre.DRAMA.name()));
        movieRepository.save(gladiatorEntity);

        List<ActorEntity> titanicActorList = generateAndSaveActorList(new ActorEntity("Leonardo DiCaprio"),
                new ActorEntity("Kate Winslet"),
                new ActorEntity("Billy Zane"),
                new ActorEntity("Kathy Bates"));

        DirectorEntity jamesCameron = new DirectorEntity("James Cameron");
        directorRepository.save(jamesCameron);

        MovieEntity titanicEntity = new MovieEntity();
        titanicEntity.setName("Titanic");
        titanicEntity.setYear("1997");
        titanicEntity.setDirector(jamesCameron);
        titanicEntity.setDuration(194L);
        titanicEntity.setMpaaRating(MpaaRating.PG13);
        titanicEntity.setActors(titanicActorList);
        titanicEntity.setGenres(String.format("%s, %s", Genre.DRAMA.name(), Genre.ROMANCE.name()));
        movieRepository.save(titanicEntity);

        List<ActorEntity> jawsActorList = generateAndSaveActorList(new ActorEntity("Roy Scheider"),
                new ActorEntity("Robert Shaw"),
                new ActorEntity("Richard Dreyfuss"),
                new ActorEntity("Lorraine Gary"));

        MovieEntity jawsEntity = new MovieEntity();
        jawsEntity.setName("Jaws");
        jawsEntity.setYear("1975");
        jawsEntity.setDirector(stevenSpielberg);
        jawsEntity.setDuration(124L);
        jawsEntity.setMpaaRating(MpaaRating.PG);
        jawsEntity.setActors(jawsActorList);
        jawsEntity.setGenres(String.format("%s, %s, %s", Genre.DRAMA.name(), Genre.ADVENTURE.name(), Genre.THRILLER.name()));
        movieRepository.save(jawsEntity);


        List<ActorEntity> theGreenMileActorList = generateAndSaveActorList(tomHanks,
                new ActorEntity("Michael Clarke Duncan"),
                new ActorEntity("David Morse"),
                new ActorEntity("Bonnie Hunt"));


        MovieEntity theGreenMileEntity = new MovieEntity();
        theGreenMileEntity.setName("The Green Mile");
        theGreenMileEntity.setYear("1999");
        theGreenMileEntity.setDirector(frankDarabont);
        theGreenMileEntity.setDuration(189L);
        theGreenMileEntity.setMpaaRating(MpaaRating.R);
        theGreenMileEntity.setActors(theGreenMileActorList);
        theGreenMileEntity.setGenres(String.format("%s, %s, %s", Genre.CRIME.name(), Genre.FANTASY.name(), Genre.DRAMA.name()));
        movieRepository.save(theGreenMileEntity);
    }

    public List<ActorEntity> generateAndSaveActorList(ActorEntity actorEntity1, ActorEntity actorEntity2,
                                                      ActorEntity actorEntity3, ActorEntity actorEntity4) {

        List<ActorEntity> list = Arrays.asList(actorEntity1, actorEntity2, actorEntity3, actorEntity4);
        Iterator<ActorEntity> it = list.iterator();
        while (it.hasNext()) {
            actorRepository.save(it.next());
        }
        return list;
    }
}
