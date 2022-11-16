package com.example.ReservationSystem.controller;

import com.example.ReservationSystem.model.*;
import com.example.ReservationSystem.repository.AvailabilityRepository;
import com.example.ReservationSystem.repository.UserRepository;
import com.example.ReservationSystem.service.ReservationService;
import com.example.ReservationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    UserService userService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AvailabilityRepository availabilityRepository;

    @GetMapping("/view/{username}")
    public List<Reservation> getByUserId(@PathVariable("username") String username) {
        if (!userRepository.existsByUserName(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user name does not exist. Check input.");
        }
        User user = userService.getUserByUserName(username);
        return reservationService.getStudentReservationAll(user.getStudent());
    }

    @PostMapping("/create/{username}")
    public Reservation createReservation(@PathVariable("username") String username, @RequestBody MakeReservationRequest reservationRequest) {
        if (!userRepository.existsByUserName(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user name does not exist. Check input.");
        }
        User user = userService.getUserByUserName(username);
        if (user.getStudent() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "student does not exist. Check input.");
        }
        if (!availabilityRepository.existsById(reservationRequest.getAvailabilityId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "availability does not exist. Check input.");
        }
        Availability availability = availabilityRepository.findById(reservationRequest.getAvailabilityId()).get();
        return reservationService.createReservation(user.getStudent(), availability, reservationRequest.getDescription());
    }

}
