package com.example.testapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created By G900 on 13-Jan-18
 */
@Entity(name = "Actor")
@Table(name = "Actor")
public class ActorEntity {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActorEntity(String name) {
        this.name = name;

    }
    public ActorEntity() {

    }
}
