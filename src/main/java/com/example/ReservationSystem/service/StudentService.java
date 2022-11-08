package com.example.ReservationSystem.service;


import com.example.ReservationSystem.model.TimeSlot;
import com.example.ReservationSystem.model.Student;
import com.example.ReservationSystem.repository.TimeSlotRepository;
import com.example.ReservationSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;

@Component
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Transactional
    public String addReservationToStudent(Long studentId, Long timeslotId) {
        // first check if the student id is valid
        Student studentById;
        if (!studentRepository.findStudentById(studentId).isPresent()) {
            throw new IllegalArgumentException("Student id does not exist");
        } else {
            studentById = studentRepository.findStudentById(studentId).get();
        }
        // then check if the timeslot is valid
        TimeSlot timeSlot;
        if (!timeSlotRepository.findTimeSlotById(timeslotId).isPresent()) {
            throw new IllegalArgumentException("Time slot id does not exist.");
        } else {
            timeSlot = timeSlotRepository.findTimeSlotById(timeslotId).get();
        }
        // check if the timeslot is still open
        if (timeSlot.isBooked()) return "This timeslot has been reserved. Select a new one.";
        // update the reservation info for this timeslot
        timeSlot.setBooked(true);
        timeSlot.setStudent(studentById);
        timeSlotRepository.save(timeSlot);

        return "Reservation is successful.";
    }

    public List<TimeSlot> getReservationsFromStudent(Long studentId) {
        // first check if the student id is valid
        Student studentById;
        if (!studentRepository.findStudentById(studentId).isPresent()) {
            throw new IllegalArgumentException("Student id does not exist");
        } else {
            studentById = studentRepository.findStudentById(studentId).get();
        }
        return studentById.getMyReservations();
    }
}
