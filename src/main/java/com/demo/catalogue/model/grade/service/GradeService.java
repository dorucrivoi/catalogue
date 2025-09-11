package com.demo.catalogue.model.grade.service;

import com.demo.catalogue.model.grade.entity.Grade;
import com.demo.catalogue.model.grade.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade createGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade getById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException("Grade not found with id " + id));
    }

    public Grade updateGrade(Long id, Grade updatedGrade) {
        return gradeRepository.findById(id).map(existingGrade -> {
            existingGrade.setGradeValue(updatedGrade.getGradeValue());
            existingGrade.setDate(updatedGrade.getDate());
            existingGrade.setStudent(updatedGrade.getStudent());
            existingGrade.setDiscipline(updatedGrade.getDiscipline());
            existingGrade.setProfessorCode(updatedGrade.getProfessorCode());
            existingGrade.setSemester(updatedGrade.getSemester());
            return gradeRepository.save(existingGrade);
        }).orElseThrow(() -> new GradeNotFoundException("Grade not found with id " + id));
    }

    public void deleteGrade(Long id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException("Grade not found with id " + id));
        gradeRepository.delete(grade);
    }
}
