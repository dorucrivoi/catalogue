package com.demo.catalogue.model.semester.service;

import com.demo.catalogue.model.semester.entity.Semester;
import com.demo.catalogue.model.semester.repository.SemesterRepository;
import com.demo.catalogue.model.student.service.StudentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {

    private static final Logger logger = LoggerFactory.getLogger(SemesterService.class);

    private final SemesterRepository semesterRepository;

    public SemesterService(SemesterRepository repo) {
        this.semesterRepository = repo;
    }

    @Transactional
    public Semester create(Semester s) {
        logger.info("Creating new semester with name {}", s.getName());
        return semesterRepository.save(s);
    }

    public List<Semester> getAll() {
        logger.debug("Fetching all semesters");
        return semesterRepository.findAll();
    }

    public Semester getById(Long id) {
        logger.debug("Fetching semester with id {}", id);
        return semesterRepository.findById(id)
                .orElseThrow(() -> new SemesterNotFoundException("Semester not found with id " + id));
    }

    @Transactional
    public Semester update(Long id, Semester s) {
        return semesterRepository.findById(id).map(existing -> {
            existing.setName(s.getName());
            existing.setCatalogue(s.getCatalogue());
            logger.info("Updated semester with id {}", id);
            return semesterRepository.save(existing);
        }).orElseThrow(() -> new SemesterNotFoundException("Semester not found with id " + id));
    }

    @Transactional
    public void delete(Long id) {
        if (!semesterRepository.existsById(id)) {
            throw new SemesterNotFoundException("Semester not found with id " + id);
        }
        logger.info("Deleting semester with id {}", id);
        semesterRepository.deleteById(id);
    }
}
