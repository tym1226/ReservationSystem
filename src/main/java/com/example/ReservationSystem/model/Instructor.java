package com.example.ReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
@Data

public class Instructor {

    /* fields */
    @Id
    @Column(name = "instructor_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "instructor_name")
    private String name;


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    @MapsId
    @JsonBackReference("user-instructor")
    User user;

    @Column(name = "Expertise")
    String expertise;

}
