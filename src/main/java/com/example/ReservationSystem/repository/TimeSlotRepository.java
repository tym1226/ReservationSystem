package com.example.ReservationSystem.repository;

import com.example.ReservationSystem.model.TimeSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {

    Optional<TimeSlot> findTimeSlotById(Long id);

    @Query(value = "SELECT * FROM TimeSlot WHERE instructor_id = :id", nativeQuery = true)
    Optional<List<TimeSlot>> findAvailabilitiesByInstructorId(Long id);

    @Query(value = "SELECT * FROM TimeSlot WHERE student_id = :id", nativeQuery = true)
    Optional<List<TimeSlot>> findReservationsByStudentId(Long id);

}
