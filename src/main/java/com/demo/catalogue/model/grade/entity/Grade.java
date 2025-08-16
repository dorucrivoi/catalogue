package com.demo.catalogue.model.grade.entity;

import com.demo.catalogue.model.discipline.entity.Discipline;
import com.demo.catalogue.model.semester.entity.Semester;
import com.demo.catalogue.model.student.entity.Student;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "GRADE")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private String professorCode;

    private BigDecimal gradeValue;

    private LocalDate date;

}
