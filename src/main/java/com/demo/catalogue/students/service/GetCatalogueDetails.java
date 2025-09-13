package com.demo.catalogue.students.service;

import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.demo.catalogue.model.student.service.StudentService;
import com.demo.catalogue.students.controller.StudentsController;
import com.demo.catalogue.students.mapper.GradeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetCatalogueDetails {

    private static final Logger logger = LoggerFactory.getLogger(GetCatalogueDetails.class);
    private final StudentService studentService;

    @Autowired
    public GetCatalogueDetails(StudentService studentService) {
        this.studentService = studentService;
    }

    public Catalogue getCatalogueForStudent(String studentCode) {
        logger.info("Get catalogue for a student: {}", studentCode);
        return studentService.getCatalogueForStudent(studentCode);
    }
}
