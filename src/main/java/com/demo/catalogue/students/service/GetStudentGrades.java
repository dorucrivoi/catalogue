package com.demo.catalogue.students.service;

import com.demo.catalogue.common.GlobalExceptionHandler;
import com.demo.catalogue.model.student.service.StudentService;
import com.demo.catalogue.students.mapper.GradeMapper;
import com.example.model.GradeResponse;
import com.example.model.GradesStudentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetStudentGrades {

    private static final Logger logger = LoggerFactory.getLogger(GetStudentGrades.class);

    private final StudentService studentService;
    private final GradeMapper gradeMapper;

    @Autowired
    public GetStudentGrades(StudentService studentService, GradeMapper gradeMapper) {
        this.studentService = studentService;
        this.gradeMapper = gradeMapper;
    }

    public List<GradesStudentResponse> getStudentGrades(String classCode) {
        logger.info("Get grades for a student: {}", classCode);
        return gradeMapper.toGradesStudentResponseList(studentService.getAllGradesByStudentCode(classCode));
    }
}
