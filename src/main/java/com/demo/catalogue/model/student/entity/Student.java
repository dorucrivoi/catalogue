package com.demo.catalogue.model.student.entity;

import com.demo.catalogue.model.grade.entity.Grade;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String catalogueCode;

    @Column(unique = true)
    private String studentCode;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades;
}
