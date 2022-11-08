package com.example.ReservationSystem.controller;

import com.example.ReservationSystem.model.TimeSlot;
import com.example.ReservationSystem.repository.TimeSlotRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    TimeSlotRepository timeSlotRepository;

    @GetMapping("/get-all")
    public List<TimeSlot> getAllTimeSlots() {
        System.out.println("test");
        return Lists.newArrayList(timeSlotRepository.findAll());
    }
}
