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

    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id).orElseThrow();
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
        }).orElseThrow(() -> new RuntimeException("Grade not found with id " + id));
    }

    public void deleteGrade(Long id) {
        if (!gradeRepository.existsById(id)) {
            throw new RuntimeException("Grade not found with id " + id);
        }
        gradeRepository.deleteById(id);
    }
}
