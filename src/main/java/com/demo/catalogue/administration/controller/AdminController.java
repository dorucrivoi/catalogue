package com.demo.catalogue.administration.controller;

import com.demo.catalogue.administration.service.ManageStudents;
import com.demo.catalogue.common.GlobalExceptionHandler;
import com.demo.catalogue.model.discipline.entity.Discipline;
import com.demo.catalogue.model.discipline.service.DisciplineService;
import com.demo.catalogue.model.student.entity.Student;
import com.example.api.AdminApi;
import com.example.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController implements AdminApi {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final DisciplineService disciplineService;
    private final ManageStudents manageStudents;

    @Autowired
    public AdminController(DisciplineService disciplineService, ManageStudents manageStudents) {
        this.disciplineService = disciplineService;
        this.manageStudents = manageStudents;
    }

    //apel prin resttemplate la didactic personal

    // microservice cu springcloud care sa se conecteze la github unde sunt salvate configurarile fisierele
    // daca nu se poate configura la git, sa fie ceva default de tip localhost
    // de scris teste la sfarsit
    //din loguri sa se vada crearea clasei, catalogului
    // la H2 pot pune un script care insereaza in database
    // care sa insereze datele start-up script sql pentru creare date initiale

    //readme de explicat continutul pachetelor si alegerea design-ului

    @Override
    public ResponseEntity<Void> createStudent(CreateStudentRequest request) {
        manageStudents.createStudent(toEntity(request));
        logger.info("Create student with student code = {}", request.getStudentCode());
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
        logger.info("Update student with student code = {}", request.getStudentCode());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteStudent(Integer id) {
        manageStudents.removeStudent(id.longValue());
        logger.info("Delete student with id={}", id);
        return ResponseEntity.noContent().build();
    }

    public static Student toEntity(CreateStudentRequest request) {
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

    @Override
    public  ResponseEntity<Void> createDiscipline(@RequestBody CreateDisciplineRequest createDisciplineRequest) {
         disciplineService.create(toEntity(createDisciplineRequest));
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<Void> updateDiscipline(Integer id,  UpdateDisciplineRequest updateDisciplineRequest) {
         disciplineService.update(id.longValue(), toEntity(updateDisciplineRequest));
        return ResponseEntity.status(201).build();
    }


    @Override
    public ResponseEntity<List<DisciplineResponse>> getAllDisciplines() {
        List<DisciplineResponse> responses = disciplineService.getAll().stream()
                .map(discipline -> {
                    DisciplineResponse response = new DisciplineResponse();
                    response.setId(discipline.getId().intValue());
                    response.setName(discipline.getName());
                    response.setDisciplineCode(discipline.getCode());
                    return response;
                })
                .toList();

        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<Void> deleteDiscipline(Integer  id) {
        disciplineService.delete(id.longValue());
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<DisciplineResponse> getDisciplineById(Integer id){
        Discipline discipline = disciplineService.getById(id.longValue());
        DisciplineResponse response = new DisciplineResponse();
        response.setId(discipline.getId().intValue());
        response.setName(discipline.getName());
        response.setDisciplineCode(discipline.getCode());
        return ResponseEntity.ok(response);

    }

    public static Discipline toEntity(CreateDisciplineRequest request) {
        Discipline discipline = new Discipline();
        discipline.setName(request.getName());
        discipline.setCode(request.getDisciplineCode());
        return discipline;
    }

    public static Discipline toEntity(UpdateDisciplineRequest request) {
        Discipline discipline = new Discipline();
        discipline.setName(request.getName());
        discipline.setCode(request.getDisciplineCode());
        return discipline;
    }
}

