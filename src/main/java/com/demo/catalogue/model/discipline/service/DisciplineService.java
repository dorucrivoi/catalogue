package com.demo.catalogue.model.discipline.service;

import com.demo.catalogue.model.discipline.entity.Discipline;
import com.demo.catalogue.model.discipline.repository.DisciplineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository repo) {
        this.disciplineRepository = repo;
    }

    public Discipline create(Discipline d) {
        return disciplineRepository.save(d);
    }

    public List<Discipline> getAll() {
        return disciplineRepository.findAll();
    }

    public Discipline getById(Long id) {
        return disciplineRepository.findById(id).orElseThrow();
    }

    public Discipline update(Long id, Discipline d) {
        if (!disciplineRepository.existsById(id)) {
            throw new RuntimeException("Discipline not found with id " + id);
        }
        Discipline existing = getById(id);
        existing.setName(d.getName());
        existing.setCode(d.getCode());
        return disciplineRepository.save(existing);
    }

    public void delete(Long id) {
        disciplineRepository.deleteById(id);
    }
}
