package com.demo.catalogue.model.discipline.repository;

import com.demo.catalogue.model.discipline.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
