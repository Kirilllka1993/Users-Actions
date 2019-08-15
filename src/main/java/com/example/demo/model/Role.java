package com.example.demo.model;


import javax.persistence.*;

@Entity
@Table(name = "role")
public enum Role {

    ADMIN,
    USER;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    Role(){

    }

}
