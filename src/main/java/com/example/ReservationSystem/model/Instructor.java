package com.example.ReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "instructor")
@Data

public class Instructor {

    /* fields */
    @Id
    @Column(name = "instructor_id")
    private Long id;
    private String name;

    @OneToMany (mappedBy = "instructor")
    @JsonManagedReference
    private List<TimeSlot> myAvailabilities = new ArrayList<>();

    public Instructor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
