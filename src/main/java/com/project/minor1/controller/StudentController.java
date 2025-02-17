package com.project.minor1.controller;

import com.project.minor1.model.Book;
import com.project.minor1.model.Student;
import com.project.minor1.request.BookCreateRequest;
import com.project.minor1.request.StudentCreateRequest;
import com.project.minor1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.SecureCacheResponse;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/create")
    public Student createStudent(@RequestBody StudentCreateRequest studentCreateRequest){
        return studentService.createStudent(studentCreateRequest);
    }
}
