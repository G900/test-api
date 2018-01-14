package com.example.testapi.entity;

import com.example.testapi.helper.Genre;
import com.example.testapi.helper.MpaaRating;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created By G900 on 12-Jan-18
 */
@Entity(name = "Movie")
@Table(name = "Movie")
public class MovieEntity {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "MpaaRating")
    private MpaaRating mpaaRating;

    @Column(name = "Duration")
    private Long duration;

    @Column(name = "Genres")
    private String genres ;

    @Column(name = "Year")
    private String year;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Director")
    private DirectorEntity director;


    @Column(name = "Actors")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ActorId")
    private List<ActorEntity> actors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public DirectorEntity getDirector() {
        return director;
    }

    public void setDirector(DirectorEntity director) {
        this.director = director;
    }

    public List<ActorEntity> getActors() {
        return actors;
    }

    public void setActors(List<ActorEntity>actors) {
        this.actors = actors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieEntity(){}


}
