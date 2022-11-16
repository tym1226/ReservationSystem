package com.example.ReservationSystem.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MakeReservationRequest {

    @NotNull
    Long availabilityId;

    @NotEmpty
    String description;

}
