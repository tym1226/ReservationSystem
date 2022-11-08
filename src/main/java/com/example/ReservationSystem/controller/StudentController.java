package com.example.ReservationSystem.controller;

import com.example.ReservationSystem.model.TimeSlot;
import com.example.ReservationSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add-reservation/{studentId}/{availabilityId}")
    public String addAvailabilityToInstructor (@PathVariable Long studentId, @PathVariable Long availabilityId) {
        return studentService.addReservationToStudent(studentId, availabilityId);
    }

    @GetMapping("/get-reservations/{studentId}")
    public List<TimeSlot> getReservationsFromStudent(@PathVariable Long studentId) {
        return studentService.getReservationsFromStudent(studentId);
    }
}
