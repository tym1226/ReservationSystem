package com.example.ReservationSystem.service;

import com.example.ReservationSystem.model.TimeSlot;
import com.example.ReservationSystem.model.Instructor;
import com.example.ReservationSystem.repository.TimeSlotRepository;
import com.example.ReservationSystem.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    TimeSlotRepository timeSlotRepository;

    public String addAvailabilityToInstructor(Long instructorId, LocalDateTime startTime, LocalDateTime endTime) {
        Instructor instructorById;
        if (!instructorRepository.findInstructorById(instructorId).isPresent()) {
            throw new IllegalArgumentException("Instructor id does not exist.");
        } else {
            instructorById = instructorRepository.findInstructorById(instructorId).get();
        }
        // creating this new timeslot and saving the timeslot to the timeslot repo
        TimeSlot timeSlot = new TimeSlot(instructorById, startTime, endTime);

        timeSlot.setInstructor(instructorById);
        timeSlotRepository.save(timeSlot);
        return "Availability added.";
    }

    public List<TimeSlot> getAvailabilitiesFromInstructor(Long instructorId) {
        Instructor instructorById;
        if (!instructorRepository.findInstructorById(instructorId).isPresent()) {
            throw new IllegalArgumentException("Instructor id does not exist.");
        } else {
            instructorById = instructorRepository.findInstructorById(instructorId).get();
        }
        return instructorById.getMyAvailabilities();
    }
}
