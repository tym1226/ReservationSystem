package com.example.ReservationSystem.repository;

import com.example.ReservationSystem.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository {

    boolean existsByUserName(String userName);

    User findUserByUserName(String userName);
}
