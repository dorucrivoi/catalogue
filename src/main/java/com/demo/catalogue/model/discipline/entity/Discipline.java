package com.demo.catalogue.model.discipline.entity;

import com.demo.catalogue.model.grade.entity.Grade;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "DISCIPLINE")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DISCIPLINE_CODE", unique = true)

    private String disciplineCode;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<Grade> grades;
}
