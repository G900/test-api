package com.example.testapi.contrloller;


import com.example.testapi.command.GetMoviesByParametersCommand;
import com.example.testapi.result.MovieResult;
import com.example.testapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created By G900 on 12-Jan-18
 */
@RestController
@RequestMapping(CinemaController.URL)
public class CinemaController {
    public static final String URL = "v1/cinema";
    private final MovieService movieService;

    @Autowired
    public CinemaController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/listmovies")
    public List<MovieResult> getMovies(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "director", required = false) String director,
                                       @RequestParam(value = "year", required = false) String year,
                                       @RequestParam(value = "mpaarating", required = false) String mpaaRating,
                                       @RequestParam(value = "genres", required = false) String[] genres,
                                       @RequestParam(value = "actors", required = false) String[] actors
    ) throws Exception {
        GetMoviesByParametersCommand command = new GetMoviesByParametersCommand();
        command.setDirector(director);
        command.setName(name);
        command.setYear(year);
        command.setMpaaRating(mpaaRating);
        command.setGenres(genres == null ? null : Arrays.asList(genres));
        command.setActors(actors == null ? null : Arrays.asList(actors));

        return movieService.getMoviesByParameters(command);
    }


}
