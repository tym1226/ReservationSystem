package com.example.ReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "student")
@Data
@Table
public class Student {

    /* fields */
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "student_name")
    private String name;

    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "user_id")
    @MapsId
    @JsonBackReference("user-student")
    User user;

}
