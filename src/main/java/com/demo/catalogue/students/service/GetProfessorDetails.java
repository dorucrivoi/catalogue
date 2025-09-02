package com.demo.catalogue.students.service;

import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.demo.catalogue.model.student.service.StudentCatalogueNotFoundException;
import com.demo.catalogue.model.student.service.StudentService;
import com.demo.catalogue.students.mapper.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProfessorDetails {

    private final StudentService studentService;
    private final GradeMapper gradeMapper;

    @Autowired
    public GetProfessorDetails(StudentService studentService, GradeMapper gradeMapper) {
        this.studentService = studentService;
        this.gradeMapper = gradeMapper;
    }

    public Catalogue getCatalogueForStudent(String studentCode) {
        return studentService.getCatalogueForStudent(studentCode);
    }
}
