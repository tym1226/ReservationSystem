package com.example.ReservationSystem.repository;

import com.example.ReservationSystem.model.Availability;
import com.example.ReservationSystem.model.Instructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Long> {

    @Query(value = "SELECT a from Availability a " + "WHERE a.startTime >= :from AND a.endTime < :to")
    List<Availability> findBetween(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query(value = "SELECT a from Availability a " + "WHERE a.instructor = :instructor AND" +
            "a.startTime >= :from AND a.endTime < :to")
    List<Availability> findByInstructorBetween(@Param("instructor") Instructor instructor, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query(value = "SELECT a from Availability a " + "WHERE a.instructor = :instructor")
    List<Availability> findByInstructor(@Param("instructor") Instructor instructor);


}
