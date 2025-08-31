package com.demo.catalogue.professors;

import com.demo.catalogue.model.grade.entity.Grade;
import com.example.api.ProfessorsApi;
import com.example.model.CreateGradeRequest;
import com.example.model.GradeResponse;
import com.example.model.UpdateGradeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/api")
//@RequestMapping("/api/admin/disciplines")
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
}

