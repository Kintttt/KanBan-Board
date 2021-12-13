package com.week9.taskmanagerapp.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Person {

    @Id
    @SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private  Long personId;
    @Column(nullable = false, columnDefinition = "text")
    private  String firstName;
    @Column(nullable = false, columnDefinition = "text")
    private String lastName;
    @Column(nullable = false, columnDefinition = "text", updatable = false)
    private  String email;
    @Column(nullable = false, columnDefinition = "text")
    private  String password;

    public Person(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Person() {
    }
}
