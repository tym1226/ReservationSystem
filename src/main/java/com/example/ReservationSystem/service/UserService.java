package com.example.ReservationSystem.service;

import com.example.ReservationSystem.model.CreateUserRequest;
import com.example.ReservationSystem.model.Instructor;
import com.example.ReservationSystem.model.Student;
import com.example.ReservationSystem.model.User;
import com.example.ReservationSystem.repository.InstructorRepository;
import com.example.ReservationSystem.repository.StudentRepository;
import com.example.ReservationSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    InstructorRepository instructorRepository;

    /**
     *
     * @param userName, passWord, role, fullName
     * @return registered user if successful and also adds to student or instructor repo based on role
     * the user register with role only requires basic info, for additional info such as instructor's expertise, call
     * addAdditionalInfo methods
     */
    public User createUser(String userName, String passWord, String role, String fullName) {
        // the name double-booking is dealt with in controller
//        if (userRepository.existsByUserName(userName)) {
//            throw new IllegalArgumentException("User name already exists. Try a new one.");
//        }
        User user = new User(userName, passWord, role);
        userRepository.save(user);

        /* update student or instructor info respectively */
        if (role == "student") {
            updateStudentProfile(user, fullName);
        } else if (role == "instructor") {
            updateInstructorProfile(user, fullName);
        }
        return user;
    }

    private void updateStudentProfile(User user, String name) {
        Student student = new Student();
        student.setUser(user);
        student.setName(name);
        studentRepository.save(student);
    }

    private void updateInstructorProfile(User user, String name) {
        Instructor instructor = new Instructor();
        instructor.setUser(user);
        instructor.setName(name);
        instructorRepository.save(instructor);
    }

    public Instructor addInfoToInstructor(Instructor instructor, String expertise) {
        String curr = instructor.getExpertise();
        if (curr == null) {
            instructor.setExpertise(expertise);
        } else {
            curr += " " + expertise;
            instructor.setExpertise(curr);
        }
        instructorRepository.save(instructor);
        return instructor;
    }


    /**
     *
     * @param userName
     * @return user info based on userName
     */
    public User getUserByUserName(String userName) {
//        if (!userRepository.existsByUserName(userName)) {
//            throw new IllegalArgumentException("Username does not exist. Check spelling.");
//        }
        return userRepository.findUserByUserName(userName);
    }





}
