package com.example.testapi.repository;

import com.example.testapi.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created By G900 on 12-Jan-18
 */
public interface MovieRepository extends JpaRepository<MovieEntity, UUID>{
}
