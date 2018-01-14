package com.example.testapi.entity;

import javax.persistence.*;

/**
 * Created By G900 on 14-Jan-18
 */
@Entity(name = "Director")
@Table(name = "Director")
public class DirectorEntity {
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

    public DirectorEntity(String name) {
        this.name = name;

    }
    public DirectorEntity() {
    }
}
