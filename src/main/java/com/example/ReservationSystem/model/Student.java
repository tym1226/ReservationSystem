package com.example.ReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@Data
@Table(name = "student")
public class Student {

    /* fields */
    @Id
    @Column(name = "student_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private List<TimeSlot> myReservations = new ArrayList<>();

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
