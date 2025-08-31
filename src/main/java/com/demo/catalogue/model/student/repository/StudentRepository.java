package com.demo.catalogue.model.student.repository;

import com.demo.catalogue.model.grade.entity.Grade;
import com.demo.catalogue.model.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = """
        SELECT g.* 
        FROM grade g
            JOIN student s ON g.student_id = s.id
            WHERE s.code = :studentCode
        """, nativeQuery = true)
    List<Grade> findAllGradesByStudentCode(@Param("studentCode") String studentCode);
}
