package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "status")
public enum Status {
    ACTIVE,
    INACTIVE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    Status() {
    }
}
