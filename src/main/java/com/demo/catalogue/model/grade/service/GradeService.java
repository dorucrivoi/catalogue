package com.demo.catalogue.model.grade.service;

import com.demo.catalogue.model.discipline.service.DisciplineService;
import com.demo.catalogue.model.grade.entity.Grade;
import com.demo.catalogue.model.grade.repository.GradeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GradeService {


    private static final Logger logger = LoggerFactory.getLogger(GradeService.class);
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Transactional
    public Grade createGrade(Grade grade) {
        logger.info("Creating new grade for student {} in discipline {}",
                grade.getStudent().getId(), grade.getDiscipline().getId());
        return gradeRepository.save(grade);
    }

    public List<Grade> getAllGrades() {
        logger.debug("Fetching all grades");
        return gradeRepository.findAll();
    }

    public Grade getById(Long id) {
        logger.debug("Fetching grade with id {}", id);
        return gradeRepository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException("Grade not found with id " + id));
    }

    @Transactional
    public Grade updateGrade(Long id, Grade updatedGrade) {
        return gradeRepository.findById(id).map(existingGrade -> {
            existingGrade.setGradeValue(updatedGrade.getGradeValue());
            existingGrade.setDate(updatedGrade.getDate());
            existingGrade.setStudent(updatedGrade.getStudent());
            existingGrade.setDiscipline(updatedGrade.getDiscipline());
            existingGrade.setProfessorCode(updatedGrade.getProfessorCode());
            existingGrade.setSemester(updatedGrade.getSemester());

            logger.info("Updated grade with id {}", id);
            return gradeRepository.save(existingGrade);
        }).orElseThrow(() -> new GradeNotFoundException("Grade not found with id " + id));
    }

    @Transactional
    public void deleteGrade(Long id) {
        if (!gradeRepository.existsById(id)) {
            throw new GradeNotFoundException("Grade not found with id " + id);
        }
        logger.info("Deleting grade with id {}", id);
        gradeRepository.deleteById(id);
    }
}
