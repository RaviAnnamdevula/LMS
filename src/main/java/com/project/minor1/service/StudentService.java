package com.project.minor1.service;

import com.project.minor1.model.*;
import com.project.minor1.repository.StudentRepository;
import com.project.minor1.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public Student createStudent(StudentCreateRequest studentCreateRequest) {
        List<Student> studentListFromDb = studentRepository.findByPhoneNo(studentCreateRequest.getPhoneNo());
        Student studentFromDb =null;
        if(studentListFromDb == null || studentListFromDb.isEmpty()){
            studentFromDb = studentRepository.save(studentCreateRequest.toStudent());
            return studentFromDb;
        }
        studentFromDb = studentListFromDb.get(0);
        return studentFromDb;
    }

    public List<Student> filter(StudentFilterType filterBy, Operator operator, String value) {
        switch (operator) {
            case EQUALS:
                switch (filterBy) {
                    case CONTACT:
                        return studentRepository.findByPhoneNo(value);
                }
            default:
                return new ArrayList<>();
        }
    }
}
