package com.example.testapi.entity;

import com.example.testapi.helper.Genre;
import com.example.testapi.helper.MpaaRating;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created By G900 on 12-Jan-18
 */
@Entity(name = "Movie")
@Table(name = "Movie")
public class MovieEntity {

    @Id
    @Type(type = "uuid-custom")
    @Column(name = "Id")
    private UUID id;

    @Column(name = "MpaaRating")
    @Enumerated(EnumType.STRING)
    private MpaaRating mpaaRating;

    @Column(name = "Duration")
    private String duration;

    @Column(name = "Genres")
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    @Column(name = "PremiereDate")
    private Date premiereDate;

    @Column(name = "Director")
    private String director;

    @Column(name = "Writer")
    private String writer;

    @Column(name = "Stars")
    private List<String> stars;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Date getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(Date premiereDate) {
        this.premiereDate = premiereDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public List<String> getStars() {
        return stars;
    }

    public void setStars(List<String> stars) {
        this.stars = stars;
    }


}
