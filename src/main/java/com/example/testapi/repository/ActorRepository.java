package com.example.testapi.repository;

import com.example.testapi.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By G900 on 13-Jan-18
 */
public interface ActorRepository  extends JpaRepository<ActorEntity, Long> {
}
