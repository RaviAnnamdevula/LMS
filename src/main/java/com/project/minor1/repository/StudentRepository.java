package com.project.minor1.repository;

import com.project.minor1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Integer> {

  //  Student findByPhoneNo(String phoneNo);

    List<Student> findByPhoneNo(String value);
}
