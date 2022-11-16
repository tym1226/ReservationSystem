package com.example.ReservationSystem.service;

import com.example.ReservationSystem.model.Availability;
import com.example.ReservationSystem.model.Reservation;
import com.example.ReservationSystem.model.Student;
import com.example.ReservationSystem.repository.AvailabilityRepository;
import com.example.ReservationSystem.repository.ReservationRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    AvailabilityRepository availabilityRepository;

    public List<Reservation> getStudentReservationAll(Student student) {
        return reservationRepository.findAllByStudentId(student.getId());
    }

    public List<Reservation> getStudentReservationBetween(Student student, LocalDateTime from, LocalDateTime to) {
        return reservationRepository.findByStudentAndBetween(student, from, to);
    }

    public Iterable<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    public Reservation createReservation(Student student, Availability availability, String description) {
        Reservation reservation = Reservation.builder().student(student)
                .StartTime(availability.getStartTime()).EndTime(availability.getEndTime())
                .description(description)
                .availabilityList(List.of(availability)).build();
        reservationRepository.save(reservation);
        availabilityRepository.save(availability);
        return reservation;
    }
}
