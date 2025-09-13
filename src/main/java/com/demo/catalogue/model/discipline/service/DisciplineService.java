package com.demo.catalogue.model.discipline.service;

import com.demo.catalogue.model.discipline.entity.Discipline;
import com.demo.catalogue.model.discipline.repository.DisciplineRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService {

    private static final Logger logger = LoggerFactory.getLogger(DisciplineService.class);
    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository repo) {
        this.disciplineRepository = repo;
    }

    @Transactional
    public Discipline create(Discipline d) {
        logger.info("Creating discipline: {}", d.getName());
        return disciplineRepository.save(d);
    }

    public List<Discipline> getAll() {
        logger.debug("Fetching all disciplines");
        return disciplineRepository.findAll();
    }

    public Discipline getById(Long id) {
        logger.debug("Fetching discipline with id: {}", id);
        return disciplineRepository.findById(id)
                .orElseThrow(() -> new DisciplineNotFoundException("Discipline not found with id " + id));
    }

    @Transactional
    public Discipline update(Long id, Discipline d) {
        Discipline existing = getById(id);
        existing.setName(d.getName());
        existing.setCode(d.getCode());
        logger.info("Updated discipline with id: {}", id);
        return disciplineRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        logger.info("Deleting discipline with id: {}", id);
        if (!disciplineRepository.existsById(id)) {
            throw new DisciplineNotFoundException("Discipline not found with id " + id);
        }
        disciplineRepository.deleteById(id);
    }
}
