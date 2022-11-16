package com.example.ReservationSystem.service;

import com.example.ReservationSystem.model.Availability;
import com.example.ReservationSystem.model.Instructor;
import com.example.ReservationSystem.model.Reservation;
import com.example.ReservationSystem.model.Student;
import com.example.ReservationSystem.repository.AvailabilityRepository;
import com.example.ReservationSystem.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;

    public List<Availability> createAvailability(Instructor instructor, LocalDateTime from, LocalDateTime to, Duration duration) {
        List<Availability> availabilityList = new ArrayList<>();
        LocalDateTime start = from, end = start.plus(duration);
        while (end.isBefore(to) || end.isEqual(to)) {
            availabilityList.add(Availability.builder().instructor(instructor)
                    .startTime(start).endTime(end).build());
            start = end;
            end = start.plus(duration);
        }
        availabilityRepository.saveAll(availabilityList);
        return availabilityList;
    }

    public List<Availability> getInstructorAvalabilitesAll(Instructor instructor) {
        return availabilityRepository.findByInstructor(instructor);
    }

    public List<Availability> getInstructorAvalabilitesBetween(Instructor instructor, LocalDateTime from, LocalDateTime to) {
        return availabilityRepository.findByInstructorBetween(instructor, from, to);
    }

    public List<Availability> getAvalabilitesBetween(LocalDateTime from, LocalDateTime to) {
        return availabilityRepository.findBetween(from, to);
    }

}
