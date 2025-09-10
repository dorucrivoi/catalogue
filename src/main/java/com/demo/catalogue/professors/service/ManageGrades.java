package com.demo.catalogue.professors.service;

import com.demo.catalogue.model.discipline.service.DisciplineService;
import com.demo.catalogue.model.grade.entity.Grade;
import com.demo.catalogue.model.grade.service.GradeService;
import com.demo.catalogue.model.semester.service.SemesterService;
import com.demo.catalogue.model.student.service.StudentService;
import com.example.model.CreateGradeRequest;
import com.example.model.UpdateGradeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ManageGrades {


    private final GradeService gradeService;
    private final StudentService studentService;
    private final SemesterService semesterService;
    private final DisciplineService disciplineService;

    @Autowired
    public ManageGrades(GradeService gradeService, StudentService studentService, SemesterService semesterService, DisciplineService disciplineService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
        this.semesterService = semesterService;
        this.disciplineService = disciplineService;
    }

    @Transactional
    public Grade createGrade(CreateGradeRequest createGradeRequest){
        return  gradeService.createGrade(mapToEntity(createGradeRequest));
    }

    @Transactional
    public Grade updateGrade(Integer id, UpdateGradeRequest updateGradeRequest){
       return gradeService.updateGrade(id.longValue(), mapToEntity(updateGradeRequest));
    }

    @Transactional
    public void deteleGrade(Integer id){
        gradeService.deleteGrade(id.longValue());
    }

    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    public Grade getGradeById(Long id) {
        return gradeService.getById(id);
    }

    private Grade mapToEntity(CreateGradeRequest request) {
        Grade grade = new Grade();
        grade.setGradeValue(BigDecimal.valueOf(request.getGradeValue()));
        grade.setDate(request.getDate());
        grade.setStudent(studentService.getById(request.getStudentId().longValue()));
        grade.setDiscipline(disciplineService.getById(request.getDisciplineId().longValue()));
        grade.setProfessorCode(request.getProfessorCode());
        grade.setSemester(semesterService.getById(request.getSemesterId().longValue()));
        return grade;
    }

    private Grade mapToEntity(UpdateGradeRequest request) {
        Grade grade = new Grade();
        grade.setGradeValue(BigDecimal.valueOf(request.getGradeValue()));
        grade.setDate(request.getDate());
        grade.setStudent(studentService.getById(request.getStudentId().longValue()));
        grade.setDiscipline(disciplineService.getById(request.getDisciplineId().longValue()));
        grade.setProfessorCode(request.getProfessorCode());
        grade.setSemester(semesterService.getById(request.getSemesterId().longValue()));
        return grade;
    }


}
