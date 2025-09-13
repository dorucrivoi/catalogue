package com.demo.catalogue.model.student.service;

import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.demo.catalogue.model.grade.entity.Grade;
import com.demo.catalogue.model.student.entity.Student;
import com.demo.catalogue.model.student.repository.StudentRepository;
import com.demo.catalogue.students.service.StudentGradesNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository repo) {
        this.studentRepository = repo;
    }

    @Transactional
    public Student createStudent(Student s) {
        Student saved = studentRepository.save(s);
        logger.info("Created student with id {}", saved.getId());
        return saved;
    }

    public List<Student> getAllStudents() {
        logger.debug("Fetching all students");
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        logger.debug("Fetching student with id {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }

    @Transactional
    public Student updateStudent(Long id, Student s) {
        Student existing = getById(id);
        existing.setName(s.getName());
        existing.setCatCode(s.getCatCode());
        existing.setCode(s.getCode());
        logger.info("Updated student with id {}", id);
        return studentRepository.save(existing);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student existing = getById(id);
        studentRepository.delete(existing);
        logger.info("Deleted student with id {}", id);
    }

    public List<Grade> getAllGradesByStudentCode(String studentCode) {
        logger.debug("Fetching grades for studentCode {}", studentCode);
        List<Grade> grades = studentRepository.findAllGradesByStudentCode(studentCode);
        if (grades.isEmpty()) {
            throw new StudentGradesNotFoundException("No grades found for studentCode " + studentCode);
        }
        return grades;
    }

    public Catalogue getCatalogueForStudent(String studentCode) {
        logger.debug("Fetching catalogue for studentCode {}", studentCode);
        return studentRepository.findCatalogueByStudentCode(studentCode)
                .orElseThrow(() -> new StudentCatalogueNotFoundException(
                        "No catalogue found for student code: " + studentCode));
    }
}
