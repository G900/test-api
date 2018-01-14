package com.example.testapi.result;

import com.example.testapi.helper.Genre;
import com.example.testapi.helper.MpaaRating;

import java.util.Collection;
import java.util.List;

/**
 * Created By G900 on 12-Jan-18
 */
public class MovieResult {
    private String name;
    private String director;
    private String year;
    private MpaaRating mpaaRating;
    private List<String> actors;
    private String genres;
    private Long duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String>actors) {
        this.actors = actors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

}
