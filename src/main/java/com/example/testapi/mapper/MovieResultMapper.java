package com.example.testapi.mapper;

import com.example.testapi.entity.ActorEntity;
import com.example.testapi.entity.MovieEntity;
import com.example.testapi.result.MovieResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By G900 on 13-Jan-18
 */
@Component
public class MovieResultMapper {
    public MovieResult toResult(MovieEntity movieEntity) {
        MovieResult movieResult = new MovieResult();

        movieResult.setName(movieEntity.getName());
        movieResult.setDirector(movieEntity.getDirector().getName());
        movieResult.setYear(movieEntity.getYear());
        movieResult.setMpaaRating(movieEntity.getMpaaRating());

        List<String> actors = new ArrayList<>();
        for (ActorEntity actorEntity : movieEntity.getActors()) {
            actors.add(actorEntity.getName());

        }
        movieResult.setActors(actors);
        movieResult.setGenres(movieEntity.getGenres());
        movieResult.setDuration(movieEntity.getDuration());
        return movieResult;
    }

    public List<MovieResult> toResultList(List<MovieEntity> movieEntities) {
        List<MovieResult> resultList = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            resultList.add(toResult(movieEntity));
        }
        return resultList;
    }
}
