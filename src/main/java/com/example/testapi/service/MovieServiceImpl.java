package com.example.testapi.service;

import com.example.testapi.command.GetMoviesByParametersCommand;

import com.example.testapi.entity.MovieEntity;
import com.example.testapi.mapper.MovieResultMapper;
import com.example.testapi.repository.MovieRepository;
import com.example.testapi.result.MovieResult;
import com.example.testapi.specification.GetMoviesByParametersSpecification;
import com.example.testapi.validator.helper.ValidationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By G900 on 12-Jan-18
 */
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    ValidationManager validationManager;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieResultMapper movieResultMapper;

    @Override
    public List<MovieResult> getMoviesByParameters(GetMoviesByParametersCommand command) throws Exception {
        validationManager.validate(command);


        GetMoviesByParametersSpecification specification = new GetMoviesByParametersSpecification(
                command.getName(), command.getYear(), command.getMpaaRating(), command.getDirector(), command.getActors(), command.getGenres());

        List<MovieEntity> fetchedMovies = movieRepository.findAll(specification);
        return movieResultMapper.toResultList(fetchedMovies);

    }
}
