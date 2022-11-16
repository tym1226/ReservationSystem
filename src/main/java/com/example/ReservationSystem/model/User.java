package com.example.ReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "\"user\"", indexes = { @Index(name = "username_index", columnList = "username", unique = true) })
// "user" is reserved in postgres
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty
    private String userName;

    private String passWord;

    private String role; //student or instructor

    @OneToOne(mappedBy = "user")
    @JsonManagedReference("user-instructor")
    Instructor instructor;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference("user-student")
    Student student;

    public User(String userName, String passWord, String role) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

}
