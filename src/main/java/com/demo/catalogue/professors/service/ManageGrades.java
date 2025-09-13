package com.demo.catalogue.professors.service;

import com.demo.catalogue.model.discipline.service.DisciplineService;
import com.demo.catalogue.model.grade.entity.Grade;
import com.demo.catalogue.model.grade.service.GradeService;
import com.demo.catalogue.model.semester.service.SemesterService;
import com.demo.catalogue.model.student.service.StudentService;
import com.example.model.CreateGradeRequest;
import com.example.model.UpdateGradeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;

@Component
public class ManageGrades {

    private static final Logger logger = LoggerFactory.getLogger(ManageGrades.class);

    private final GradeService gradeService;
    private final StudentService studentService;
    private final SemesterService semesterService;
    private final DisciplineService disciplineService;

    @Autowired
    public ManageGrades(GradeService gradeService,
                        StudentService studentService,
                        SemesterService semesterService,
                        DisciplineService disciplineService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
        this.semesterService = semesterService;
        this.disciplineService = disciplineService;
    }

    @Transactional
    public Grade createGrade(CreateGradeRequest request){
        logger.info("Creating grade");
        return gradeService.createGrade(mapToEntity(request));
    }

    @Transactional
    public Grade updateGrade(Long id, UpdateGradeRequest request){
        logger.info("Updating grade with id: {}", id);
        return gradeService.updateGrade(id, mapToEntity(request));
    }

    @Transactional
    public void deleteGrade(Long id){
        gradeService.deleteGrade(id);
        logger.info("Deleted grade with id: {}", id);
    }

    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    public Grade getGradeById(Long id) {
        logger.info("Fetching grade by id: {}", id);
        return gradeService.getById(id);
    }

    private Grade mapToEntity(CreateGradeRequest request) {
        return mapToEntity(
                request.getStudentId().longValue(),
                request.getDisciplineId().longValue(),
                request.getSemesterId().longValue(),
                request.getGradeValue(),
                request.getDate(),
                request.getProfessorCode()
        );
    }

    private Grade mapToEntity(UpdateGradeRequest request) {
        return mapToEntity(
                request.getStudentId().longValue(),
                request.getDisciplineId().longValue(),
                request.getSemesterId().longValue(),
                request.getGradeValue(),
                request.getDate(),
                request.getProfessorCode()
        );
    }

    private Grade mapToEntity(Long studentId, Long disciplineId, Long semesterId, double gradeValue, LocalDate date, String professorCode) {
        Grade grade = new Grade();
        grade.setGradeValue(BigDecimal.valueOf(gradeValue));
        grade.setDate(date);
        grade.setStudent(studentService.getById(studentId));
        grade.setDiscipline(disciplineService.getById(disciplineId));
        grade.setProfessorCode(professorCode);
        grade.setSemester(semesterService.getById(semesterId));
        return grade;
    }
}
