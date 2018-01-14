package com.example.testapi.repository;

import com.example.testapi.entity.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By G900 on 14-Jan-18
 */
public interface DirectorRepository  extends JpaRepository<DirectorEntity, Long> {
}
