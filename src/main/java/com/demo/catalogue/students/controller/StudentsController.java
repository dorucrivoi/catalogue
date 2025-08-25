package com.demo.catalogue.students.controller;

import com.demo.catalogue.model.student.entity.Student;
import com.demo.catalogue.model.student.service.StudentService;
import com.example.api.StudentsApi;
import com.example.model.ProfessorResponse;
import com.example.model.StudentRequest;
import com.example.model.StudentResponse;
import com.example.model.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentsController implements StudentsApi {
    //apel prin resttemplate la didactic personal

    // microservice cu springcloud care sa se conecteze la github unde sunt salvate configurarile fisierele
    // daca nu se poate configura la git, sa fie ceva default de tip localhost
    // de scris teste la sfarsit
    //din loguri sa se vada crearea clasei, catalogului
    // la H2 pot pune un script care insereaza in database
    // care sa insereze datele start-up script sql pentru creare date initiale

    //readme de explicat continutul pachetelor si alegerea design-ului

    private static final String BASE_URL = "http://localhost:8080/api/professors";

    private final StudentService studentService;
    private final WebClient webClient;

    @Autowired
    public StudentsController(StudentService studentService, WebClient webClient) {
        this.studentService = studentService;
        this.webClient = webClient;
    }

    @Override
    public ResponseEntity<Void> createStudent(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setCatCode(request.getCatalogueCode());
        student.setCode(request.getStudentCode());

        studentService.create(student);

        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> responses = studentService.getAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }


     @Override
     public ResponseEntity<StudentResponse> getStudentById(Integer id){
        return null;
     }

    @Override
    public ResponseEntity<Void> updateStudent(Integer id, UpdateStudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setCatCode(request.getCatalogueCode());
        student.setCode(request.getStudentCode());

        studentService.update(id.longValue(), student);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteStudent(Integer id) {
        studentService.delete(id.longValue());
        return ResponseEntity.noContent().build();
    }

    //TODO putem utiliza codul studentului pentru a extrage profesorii dontr-o clasa
    //TODO asta inseamna o interogare in plus pentru a extraga class code si anul bazat pe codul studentului

    @Override
    public ResponseEntity<List<ProfessorResponse>> getProfessorsForStudent(String classCode, Long year) {
        String url = BASE_URL + "/{classCode}/student?year={year}";

        List<ProfessorResponse> professors = webClient.get()
                .uri(url, classCode, year)
                .retrieve()
                .bodyToMono(ProfessorResponse[].class)
                .map(array -> array != null ? Arrays.asList(array) : Collections.<ProfessorResponse>emptyList())
                .block();

        return ResponseEntity.ok(professors);
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
