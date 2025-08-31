package com.demo.catalogue.students.controller;

import com.demo.catalogue.students.service.GetStudentGrades;
import com.example.api.StudentsApi;
import com.example.model.GradesStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students/studentCode")
public class ViewGradesController implements StudentsApi {

    private final GetStudentGrades studentGrades;

    @Autowired
    public ViewGradesController(GetStudentGrades studentGrades) {
        this.studentGrades = studentGrades;
    }

    @Override
    @GetMapping("/{studentCode}/grades")
    public ResponseEntity<List<GradesStudentResponse>> getGradesForStudent(String studentCode) {
        return ResponseEntity.ok(studentGrades.getStudentGrades(studentCode));
    }
}
