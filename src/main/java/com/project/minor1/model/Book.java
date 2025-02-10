package com.project.minor1.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private Integer id;

    @Column(length = 30)
    private String name;

    private String bookNo;

    private Integer cost;

    @Enumerated
    private BookType type;

    @ManyToOne
    @JoinColumn
    private Student student;

}
