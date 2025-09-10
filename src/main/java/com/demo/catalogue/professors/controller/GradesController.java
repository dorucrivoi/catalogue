package com.demo.catalogue.professors.controller;

import com.demo.catalogue.model.grade.entity.Grade;
import com.demo.catalogue.professors.service.ManageGrades;
import com.example.api.ProfessorsApi;
import com.example.model.CreateGradeRequest;
import com.example.model.GradeResponse;
import com.example.model.UpdateGradeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController
public class GradesController implements ProfessorsApi {

        private ManageGrades gradeService;

        @Autowired
        public GradesController(ManageGrades gradeService) {
            this.gradeService = gradeService;
        }

        @Override
        public ResponseEntity<Void> createGrade(CreateGradeRequest request) {
            gradeService.createGrade(request);
            return ResponseEntity.status(201).build();
        }

        @Override
        public ResponseEntity<Void> updateGrade(Integer id,  UpdateGradeRequest request) {
            gradeService.updateGrade(id, request);
            return ResponseEntity.status(201).build();
        }

        @Override
        public ResponseEntity<List<GradeResponse>> getAllGrades(){
          List<Grade> grades =  gradeService.getAllGrades();
            return ResponseEntity.ok(mapToResponseList(grades));
        }

        @Override
        public ResponseEntity<Void> deleteGrade(Integer id) {
            gradeService.deteleGrade(id);
            return ResponseEntity.ok().build();
        }

        @Override
        public ResponseEntity<GradeResponse> getGradeById(Integer id) {
            Grade grade = gradeService.getGradeById(id.longValue());
            return ResponseEntity.ok(mapToResponse(grade));
        }

        private GradeResponse mapToResponse(Grade grade) {
            GradeResponse response = new GradeResponse();
            response.setId(grade.getId().intValue());
            response.setGradeValue(grade.getGradeValue().floatValue());
            response.setDate(grade.getDate());
            response.setStudentId(grade.getStudent().getId().intValue());
            response.setDisciplineId(grade.getDiscipline().getId().intValue());
            response.setProfessorCode(grade.getProfessorCode());
            response.setSemesterId(grade.getSemester().getId().intValue());
            return response;
        }

    private List<GradeResponse> mapToResponseList(List<Grade> grades) {
        if (grades == null) {
            return Collections.emptyList();
        }
        return grades.stream()
                .map(this::mapToResponse)
                .toList();
    }
}

