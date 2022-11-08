package com.example.ReservationSystem.controller;

import com.example.ReservationSystem.model.TimeSlot;
import com.example.ReservationSystem.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/instructor/")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    @PostMapping("/add-availability/{instructorId}")
    public String addAvailabilityToInstructor (@PathVariable Long instructorId, @RequestBody LocalDateTime startTime, @RequestBody LocalDateTime endTime) {
        return instructorService.addAvailabilityToInstructor(instructorId, startTime, endTime);
    }

    @GetMapping("/get-availabilities/{instructorId}")
    public List<TimeSlot> getAvailabilitiesFromInstructor(@PathVariable Long instructorId) {
        return instructorService.getAvailabilitiesFromInstructor(instructorId);
    }
}
