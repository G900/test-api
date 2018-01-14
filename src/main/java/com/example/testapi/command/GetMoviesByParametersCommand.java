package com.example.testapi.command;

import com.example.testapi.validator.ValidateGetMoviesByParametersCommand;

import java.util.List;

/**
 * Created By G900 on 12-Jan-18
 */
@ValidateGetMoviesByParametersCommand
public class GetMoviesByParametersCommand {
    private List<String> genres;
    private String year;
    private String name;
    private String mpaaRating;
    private String director;
    private List<String> actors;

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }



}
