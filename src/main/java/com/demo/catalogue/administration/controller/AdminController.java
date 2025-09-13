package com.demo.catalogue.administration.controller;

import com.demo.catalogue.administration.mapper.CatalogueMapper;
import com.demo.catalogue.model.catalogue.service.CatalogueService;
import com.demo.catalogue.model.discipline.entity.Discipline;
import com.demo.catalogue.model.discipline.service.DisciplineService;
import com.demo.catalogue.model.student.entity.Student;
import com.demo.catalogue.model.student.service.StudentService;
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
    private final StudentService studentService;
    private final CatalogueService catalogueService;
    private final CatalogueMapper catalogueMapper;

    @Autowired
    public AdminController(DisciplineService disciplineService, StudentService manageStudents,
                           CatalogueService catalogueService, CatalogueMapper catalogueMapper) {
        this.disciplineService = disciplineService;
        this.studentService = manageStudents;
        this.catalogueService = catalogueService;
        this.catalogueMapper = catalogueMapper;
    }

    @Override
    public ResponseEntity<CatalogueResponse> getCatalogueByClassCode(String classCode, Integer year){
        logger.info("Get catalogue by classCode = {}", classCode);
        return ResponseEntity.ok(catalogueMapper.toCatalogueResponse(
                catalogueService.getCatalogueByClassCode(classCode, year)));

    }

    @Override
    public ResponseEntity<Void> createStudent(CreateStudentRequest request) {
        studentService.createStudent(toEntity(request));
        logger.info("Create student with student code = {}", request.getStudentCode());
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> responses = studentService.getAllStudents()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        logger.info("Get students");
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<StudentResponse> getStudentById(Integer id){
        logger.info("Get student by id = {}", id);
        return ResponseEntity.ok(toResponse(studentService.getById(id.longValue())));

    }

    @Override
    public ResponseEntity<Void> updateStudent(Integer id, UpdateStudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setCatCode(request.getCatalogueCode());
        student.setCode(request.getStudentCode());

        studentService.updateStudent(id.longValue(), student);
        logger.info("Update student with student code = {}", request.getStudentCode());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteStudent(Integer id) {
        studentService.deleteStudent(id.longValue());
        logger.info("Delete student with id={}", id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public  ResponseEntity<Void> createDiscipline(@RequestBody CreateDisciplineRequest createDisciplineRequest) {
        disciplineService.create(toEntity(createDisciplineRequest));
        logger.info("Create discipline with id={}", createDisciplineRequest.getDisciplineCode());
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<Void> updateDiscipline(Integer id,  UpdateDisciplineRequest updateDisciplineRequest) {
        disciplineService.update(id.longValue(), toEntity(updateDisciplineRequest));
        logger.info("Update discipline with id={}", id);
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
        logger.info("Get disciplines");
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<Void> deleteDiscipline(Integer  id) {
        disciplineService.delete(id.longValue());
        logger.info("Delete discipline with id={}", id);
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
}

