package com.demo.catalogue.model.semester.service;

import com.demo.catalogue.model.semester.entity.Semester;
import com.demo.catalogue.model.semester.repository.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {

    private final SemesterRepository semesterRepository;

    public SemesterService(SemesterRepository repo) {
        this.semesterRepository = repo;
    }

    public Semester create(Semester s) {
        return semesterRepository.save(s);
    }

    public List<Semester> getAll() {
        return semesterRepository.findAll();
    }

    public Semester getById(Long id) {
        return semesterRepository.findById(id).orElseThrow();
    }

    public Semester update(Long id, Semester s) {
        Semester existing = getById(id);
        existing.setName(s.getName());
        existing.setCatalogue(s.getCatalogue());
        return semesterRepository.save(existing);
    }

    public void delete(Long id) {
        if (!semesterRepository.existsById(id)) {
            throw new RuntimeException("semesterRepository not found with id " + id);
        }
        semesterRepository.deleteById(id);
    }
}
