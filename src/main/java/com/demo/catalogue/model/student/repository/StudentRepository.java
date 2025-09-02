package com.demo.catalogue.model.student.repository;

import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.demo.catalogue.model.grade.entity.Grade;
import com.demo.catalogue.model.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = """
        SELECT g.* 
        FROM grade g
            JOIN student s ON g.student_id = s.id
        WHERE s.code = :studentCode
        """, nativeQuery = true)
    List<Grade> findAllGradesByStudentCode(@Param("studentCode") String studentCode);

    @Query(value = """
        SELECT c.* 
        FROM CATALOGUE c
            JOIN STUDENT s 
            ON s.CAT_CODE = c.CODE
        WHERE s.CODE = :studentCode
        """, nativeQuery = true)
    Optional<Catalogue> findCatalogueByStudentCode(@Param("studentCode") String studentCode);
}
