package com.example.ReservationSystem.repository;

import com.example.ReservationSystem.model.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {

    Instructor findInstructorById(Long id);
}
