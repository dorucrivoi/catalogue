package com.demo.catalogue.students.controller;

import com.demo.catalogue.model.student.service.StudentService;
import com.example.api.StudentsApi;
import com.example.model.ProfessorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/students/classCode")
public class ViewProfessorsController implements StudentsApi {

    // TODO de creat in
    //TODO doar stundetii si profesorii au access catre catalogue si responsabilitatiele
    // catalogue si didactic personal
    // administra
// prin adaugarea actorilor admin/students se va decide rolurile
    //shallow health check  de citit  deep health check - se face in catalogue si se verifica dependeta intre servicii
    //health check indicator spring si  in catalogue - se poate adauga un ping
    private static final String BASE_URL = "http://localhost:8080/api/admin/professors";

    private final WebClient webClient;

    @Autowired
    public ViewProfessorsController(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
//    @GetMapping("/{classCode}/professors")
    public ResponseEntity<List<ProfessorResponse>> getProfessorsForStudent(String classCode, Integer year) {
        String url = BASE_URL + "/{classCode}/student?year={year}";

        List<ProfessorResponse> professors = webClient.get()
                .uri(url, classCode, year)
                .retrieve()
                .bodyToMono(ProfessorResponse[].class)
                .map(array -> array != null ? Arrays.asList(array) : Collections.<ProfessorResponse>emptyList())
                .block();

        return ResponseEntity.ok(professors);
    }
}
