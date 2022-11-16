package com.example.ReservationSystem.repository;

import com.example.ReservationSystem.model.Reservation;
import com.example.ReservationSystem.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository {

    List<Reservation> findAllByStudentId(Long student_id);

    @Query(value = "SELECT r from Reservation r WHERE r.student = :student AND "
            + "r.utcStartTime >= :from AND r.utcEndTime < :to")
    List<Reservation> findByStudentAndBetween(@Param("student") Student student, @Param("from") LocalDateTime from,
                                              @Param("to") LocalDateTime to);
}
