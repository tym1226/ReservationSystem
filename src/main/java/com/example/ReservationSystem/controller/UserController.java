package com.example.ReservationSystem.controller;

import com.example.ReservationSystem.model.CreateUserRequest;
import com.example.ReservationSystem.model.Instructor;
import com.example.ReservationSystem.model.User;
import com.example.ReservationSystem.repository.InstructorRepository;
import com.example.ReservationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    InstructorRepository instructorRepository;

    @PostMapping("register")
    public User createUser(@RequestBody CreateUserRequest newUser) {
        if (userService.getUserByUserName(newUser.getUser_name()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username already exists. Try a new one.");
        }
        return userService.createUser(newUser.getUser_name(), newUser.getPassword(), newUser.getRole(), newUser.getFull_name());
    }

    @GetMapping("user/current/{user_name}")
    public User getUser(@PathVariable("user_name") String name) {
        if (userService.getUserByUserName(name) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username does not exist. Check spelling.");
        }
        return userService.getUserByUserName(name);
    }

    @PostMapping("addExpertise/{instructor_id}")
    public Instructor addExpertise(@PathVariable("instructor_id") Long id, @RequestBody String expertise) {
        if (!instructorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "instructor id does not exist.");
        }
        Instructor instructor = instructorRepository.findInstructorById(id);
        return userService.addInfoToInstructor(instructor, expertise);
    }

//    @GetMapping("login")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String login() {
//        return "Please log in.";
//    }

}
