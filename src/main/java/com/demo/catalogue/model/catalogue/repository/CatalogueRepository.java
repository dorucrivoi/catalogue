package com.demo.catalogue.model.catalogue.repository;


import com.demo.catalogue.model.catalogue.entity.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {

    boolean existsByClassCodeAndYear(String classCode, Integer year);

    Optional<Catalogue> findByClassCodeAndYear(String classCode, int year);
}
