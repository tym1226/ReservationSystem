package com.example.ReservationSystem.repository;

import com.example.ReservationSystem.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findStudentById(Long id);
}
