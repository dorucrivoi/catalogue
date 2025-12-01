package com.demo.catalogue.students.controller;

import com.demo.catalogue.administration.controller.AdminController;
import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.demo.catalogue.students.service.GetCatalogueDetails;
import com.demo.catalogue.students.service.GetStudentGrades;
import com.example.api.StudentsApi;
import com.example.model.GradesStudentResponse;
import com.example.model.ProfessorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class StudentsController implements StudentsApi {

    private static final Logger logger = LoggerFactory.getLogger(StudentsController.class);
   //TODO de schimbat URL catre API-Gateway
    private static final String BASE_URL = "http://didactic-personal:8080/api/admin/professors";

    private final WebClient webClient;
    private final GetStudentGrades studentGrades;
    private final GetCatalogueDetails professorDetails;


    @Autowired
    public StudentsController(WebClient webClient, GetStudentGrades studentGrades,
                              GetCatalogueDetails professorDetails) {
        this.webClient = webClient;
        this.studentGrades = studentGrades;
        this.professorDetails = professorDetails;
    }

    @Override
    public ResponseEntity<List<ProfessorResponse>> getProfessorsForStudent(String studentCode, Integer year) {
        String url = BASE_URL + "/{classCode}/class?year={year}";

        Catalogue catalogue = professorDetails.getCatalogueForStudent(studentCode);
        List<ProfessorResponse> professors = webClient.get()
                .uri(url, catalogue.getClassCode(), year)
                .retrieve()
                .bodyToMono(ProfessorResponse[].class)
                .map(array -> array != null ? Arrays.asList(array) : Collections.<ProfessorResponse>emptyList())
                .block();// Using .block() is fine for a simple POC or mixed approach,
                        // as long as your own services remain synchronous.
        logger.info("Get professors for a student: {}", studentCode);
        return ResponseEntity.ok(professors);
    }

    @Override
    public ResponseEntity<List<GradesStudentResponse>> getGradesForStudent(String studentCode) {
        logger.info("Get grades for a student: {}", studentCode);
        return ResponseEntity.ok(studentGrades.getStudentGrades(studentCode));
    }
}
