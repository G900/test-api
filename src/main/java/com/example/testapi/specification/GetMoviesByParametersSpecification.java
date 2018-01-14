package com.example.testapi.specification;

import com.example.testapi.entity.ActorEntity;
import com.example.testapi.entity.DirectorEntity;
import com.example.testapi.entity.MovieEntity;
import com.example.testapi.helper.Genre;
import com.example.testapi.helper.MpaaRating;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By G900 on 12-Jan-18
 */
public class GetMoviesByParametersSpecification implements Specification<MovieEntity> {

    private final String year;
    private final String name;
    private final String mpaaRating;
    private final String director;
    private final List<String> actors;
    private final List<String> genres;

    public GetMoviesByParametersSpecification(String name, String year, String mpaaRating, String director,
                                              List<String> actors, List<String> genres) {
        this.director = director;
        this.genres = genres;
        this.mpaaRating = mpaaRating;
        this.year = year;
        this.actors = actors;
        this.name = name;


    }


    @Override
    public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (!isNull(name)) {
            Expression<String> nameExpression = root.get("name");
            predicates.add(likeIgnoreCase(criteriaBuilder, nameExpression, name));
        }
        if (!isNull(year)) {
            Expression<String> yearExpression = root.get("year");
            predicates.add(criteriaBuilder.equal(yearExpression, year));
        }
        if (!isNull(mpaaRating)) {

            Expression<MpaaRating> mpaaRatingExpression = root.get("mpaaRating");
            predicates.add(criteriaBuilder.equal(mpaaRatingExpression, MpaaRating.valueOf(mpaaRating.toUpperCase())));
        }
        if (!isNull(director)) {
            Join<MovieEntity, DirectorEntity> directorJoinExpression = root.join("director", JoinType.INNER);
            Expression<String> directorExpression = directorJoinExpression.get("name");
            predicates.add(likeIgnoreCase(criteriaBuilder, directorExpression, director));
        }


        if (!isNull(actors)) {
            for (String actor : actors) {
                Join<MovieEntity, ActorEntity> actorsJoinExpression = root.join("actors", JoinType.INNER);
                Expression<String> actorNameExpression = actorsJoinExpression.get("name");
                predicates.add(likeIgnoreCase(criteriaBuilder, actorNameExpression, actor.replace("-", " ")));
            }
        }

        if (!isNull(genres)) {
            List<String> upperCaseStringGenres = new ArrayList<>();
            genres.forEach(x -> {
                upperCaseStringGenres.add(x.toUpperCase());
            });

            List<Genre> genresEnum = upperCaseStringGenres.stream().map(Genre::valueOf).collect(Collectors.toList());
            Expression<String> genresExpression = root.get("genres");
            List<Predicate> genrePredicates = new ArrayList<>();
            for (Genre genre : genresEnum) {
                genrePredicates.add(criteriaBuilder.like(genresExpression, "%" + genre.name() + "%"));
            }
            if (genrePredicates.size() > 1) {
                predicates.add(constructAndClause(genrePredicates, criteriaBuilder));
            } else {
                predicates.add(genrePredicates.get(0));
            }

        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }

    protected boolean isNull(Object o) {
        return o == null;
    }


    private Predicate constructAndClause(List<Predicate> list, CriteriaBuilder criteriaBuilder) {
        Predicate first = criteriaBuilder.and(list.get(0), list.get(1));
        Predicate second = null;
        if (list.size() > 2) {
            for (int i = 2; i < list.size(); i++) {
                second = criteriaBuilder.and(first, list.get(i));
                first = second;
            }
        }
        return first;

    }

    private Predicate likeIgnoreCase(CriteriaBuilder criteriaBuilder, Expression<String> stringExpression, String value) {
        return criteriaBuilder.like(criteriaBuilder.lower(stringExpression), "%" + value.toLowerCase() + "%");

    }


}
