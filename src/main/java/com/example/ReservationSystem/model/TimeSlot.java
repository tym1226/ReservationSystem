package com.example.ReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "timeslot")
@Getter @Setter @NoArgsConstructor
public class TimeSlot {

    /* fields */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    private boolean isBooked;


    @ManyToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    @JsonBackReference // https://stackoverflow.com/questions/36983215/failed-to-write-http-message-org-springframework-http-converter-httpmessagenotw
    @NotNull
    // add this info when a time slot is added by an instructor
    private Instructor instructor;

    @ManyToOne (cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "student_id")
    @JsonBackReference
    // add this info if a student books this time slot
    private Student student;

    public TimeSlot(Instructor instructor, LocalDateTime startTime, LocalDateTime endTime) {
        this.instructor = instructor;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
