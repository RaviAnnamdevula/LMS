package com.project.minor1.request;

import com.project.minor1.model.Student;
import com.project.minor1.model.StudentType;
import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequest {  // DTO data transfer object

    private String name;
    private String email;
    private String phoneNo;
    private String address;

    public Student toStudent() {
        return Student.builder().name(this.name).email(this.email).phoneNo(this.phoneNo).address(this.phoneNo).status(StudentType.ACTIVE).build();
    }
}
