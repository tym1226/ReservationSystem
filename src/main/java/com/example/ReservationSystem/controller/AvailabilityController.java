package com.example.ReservationSystem.controller;

import com.example.ReservationSystem.model.*;
import com.example.ReservationSystem.repository.InstructorRepository;
import com.example.ReservationSystem.repository.UserRepository;
import com.example.ReservationSystem.service.AvailabilityService;
import com.example.ReservationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    AvailabilityService availabilityService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    UserService userService;

    @GetMapping("/viewByInstructor/{instructor_id}")
    public List<Availability> getInstructorAvalabilitesAll(@PathVariable("instructor_id") long id) {
        if (!instructorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "instructor id does not exist. Check input.");
        }
        Instructor instructor = instructorRepository.findInstructorById(id);
        return availabilityService.getInstructorAvalabilitesAll(instructor);
    }

    @GetMapping("/viewBetween")
    public List<Availability> getAvailabilitiesBetween(@RequestParam(name = "from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime from,
                                                       @RequestParam(name = "to") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime to) {
        return availabilityService.getAvalabilitesBetween(from, to);
    }

    @GetMapping("/viewByInstructorBetween/{instructor_id}")
    public List<Availability> getInstructorAvalabilitesBetween(@PathVariable("instructor_id") long id,
                                                               @RequestParam(name = "from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime from,
                                                               @RequestParam(name = "to") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime to) {
        if (!instructorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "instructor id does not exist. Check input.");
        }
        Instructor instructor = instructorRepository.findInstructorById(id);
        return availabilityService.getInstructorAvalabilitesBetween(instructor, from, to);
    }

    @PostMapping("/create/{username}")
    public List<Availability> createAvailability(@PathVariable("username") String username, @RequestBody CreateAvailabilityRequest availabilityRequest) {
        if (!userRepository.existsByUserName(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user name does not exist. Check input.");
        }
        User user = userService.getUserByUserName(username);
        if (user.getInstructor() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "instructor does not exist. Check input.");
        }
        LocalDateTime start = availabilityRequest.getStartTime();
        LocalDateTime end = availabilityRequest.getEndTime();
        if (start.isAfter(end)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "start time must be before end time.");
        }
        Duration duration = Duration.ofMinutes(availabilityRequest.getDurationMinutes());
        return availabilityService.createAvailability(user.getInstructor(), start, end, duration);
    }
}
