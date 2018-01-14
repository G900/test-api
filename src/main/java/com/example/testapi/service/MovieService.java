package com.example.testapi.service;

import com.example.testapi.command.GetMoviesByParametersCommand;
import com.example.testapi.result.MovieResult;

import java.util.List;

/**
 * Created By G900 on 12-Jan-18
 */
public interface MovieService {
    public List<MovieResult> getMoviesByParameters(GetMoviesByParametersCommand command) throws Exception;
}
