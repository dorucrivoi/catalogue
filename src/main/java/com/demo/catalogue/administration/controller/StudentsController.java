package com.demo.catalogue.administration.controller;

import com.demo.catalogue.administration.service.ManageStudents;
import com.demo.catalogue.common.GlobalExceptionHandler;
import com.demo.catalogue.model.student.entity.Student;
import com.example.api.AdminApi;
import com.example.model.StudentRequest;
import com.example.model.StudentResponse;
import com.example.model.UpdateStudentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentsController implements AdminApi {
    //apel prin resttemplate la didactic personal

    // microservice cu springcloud care sa se conecteze la github unde sunt salvate configurarile fisierele
    // daca nu se poate configura la git, sa fie ceva default de tip localhost
    // de scris teste la sfarsit
    //din loguri sa se vada crearea clasei, catalogului
    // la H2 pot pune un script care insereaza in database
    // care sa insereze datele start-up script sql pentru creare date initiale

    //readme de explicat continutul pachetelor si alegerea design-ului
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final ManageStudents manageStudents;

    @Autowired
    public StudentsController(ManageStudents manageStudents) {
        this.manageStudents = manageStudents;
    }

    @Override
    public ResponseEntity<Void> createStudent(StudentRequest request) {
        manageStudents.createStudent(toEntity(request));
        log.info("Create student with student code = {}", request.getStudentCode());
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> responses = manageStudents.findStudents()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

     @Override
     public ResponseEntity<StudentResponse> getStudentById(Integer id){
        return ResponseEntity.ok(toResponse(manageStudents.findStudent(id.longValue())));
     }

    @Override
    public ResponseEntity<Void> updateStudent(Integer id, UpdateStudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setCatCode(request.getCatalogueCode());
        student.setCode(request.getStudentCode());

        manageStudents.updateStudent(id.longValue(), student);
        log.info("Update student with student code = {}", request.getStudentCode());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteStudent(Integer id) {
        manageStudents.removeStudent(id.longValue());
        log.info("Delete student with id={}", id);
        return ResponseEntity.noContent().build();
    }

    public static Student toEntity(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setCatCode(request.getCatalogueCode());
        student.setCode(request.getStudentCode());
        return student;
    }

    private StudentResponse toResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId().intValue());
        response.setName(student.getName());
        response.setCatalogueCode(student.getCatCode());
        response.setStudentCode(student.getCode());
        return response;
    }
}
