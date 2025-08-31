package com.demo.catalogue.administration.controller;

import com.demo.catalogue.model.discipline.entity.Discipline;
import com.demo.catalogue.model.discipline.service.DisciplineService;
import com.example.api.AdminApi;
import com.example.model.CreateDisciplineRequest;
import com.example.model.DisciplineResponse;
import com.example.model.UpdateDisciplineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api") //TODO aici nu merge api pentru voi avea eroare cu api/admin/students
//@RequestMapping("/api/admin/disciplines")
public class DisciplineController implements AdminApi {


    private final DisciplineService disciplineService;

    @Autowired
    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
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

