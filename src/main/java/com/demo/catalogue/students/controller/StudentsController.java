package com.demo.catalogue.students.controller;

import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.demo.catalogue.students.service.GetCatalogueDetails;
import com.demo.catalogue.students.service.GetStudentGrades;
import com.example.api.StudentsApi;
import com.example.model.GradesStudentResponse;
import com.example.model.ProfessorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class StudentsController implements StudentsApi {

    // TODO DE MODIFICAT numele in StudentsController
    //TODO doar stundetii si profesorii au access catre catalogue si responsabilitatiele
    // catalogue si didactic personal
    // administra
// prin adaugarea actorilor admin/students se va decide rolurile
    //shallow health check  de citit  deep health check - se face in catalogue si se verifica dependeta intre servicii
    //health check indicator spring si  in catalogue - se poate adauga un ping
    private static final String BASE_URL = "http://localhost:8080/api/admin/professors";

    private final WebClient webClient;
    private final GetStudentGrades studentGrades;
    private final GetCatalogueDetails professorDetails;


    @Autowired
    public StudentsController(WebClient webClient, GetStudentGrades studentGrades, GetCatalogueDetails professorDetails) {
        this.webClient = webClient;
        this.studentGrades = studentGrades;
        this.professorDetails = professorDetails;
    }

    @Override
    public ResponseEntity<List<ProfessorResponse>> getProfessorsForStudent(String studentCode, Integer year) {
        String url = BASE_URL + "/{classCode}/student?year={year}";

        Catalogue catalogue = professorDetails.getCatalogueForStudent(studentCode);
        List<ProfessorResponse> professors = webClient.get()
                .uri(url, catalogue.getClassCode(), year)
                .retrieve()
                .bodyToMono(ProfessorResponse[].class)
                .map(array -> array != null ? Arrays.asList(array) : Collections.<ProfessorResponse>emptyList())
                .block();

        return ResponseEntity.ok(professors);
    }

    @Override
    public ResponseEntity<List<GradesStudentResponse>> getGradesForStudent(String studentCode) {
        return ResponseEntity.ok(studentGrades.getStudentGrades(studentCode));
    }
}
