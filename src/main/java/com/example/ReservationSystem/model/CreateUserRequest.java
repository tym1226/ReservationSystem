package com.example.ReservationSystem.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class CreateUserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String user_name;

    String password;

    String role; // "student" or “instructor"

    String full_name;

}
