package com.demo.catalogue.model.student.service;

import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.demo.catalogue.model.grade.entity.Grade;
import com.demo.catalogue.model.student.entity.Student;
import com.demo.catalogue.model.student.repository.StudentRepository;
import com.demo.catalogue.students.service.StudentGradesNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository repo) {
        this.studentRepository = repo;
    }

    public Student createStudent(Student s) {
        return studentRepository.save(s);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }

    public Student updateStudent(Long id, Student s) {
        Student existing = getById(id);
        existing.setName(s.getName());
        existing.setCatCode(s.getCatCode());
        existing.setCode(s.getCode());
        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        Student existing = getById(id); // avoids duplicate DB calls
        studentRepository.delete(existing);
    }

    public List<Grade> getAllGradesByStudentCode(String studentCode){
            List<Grade> grades = studentRepository.findAllGradesByStudentCode(studentCode);
            if (grades.isEmpty()) {
                throw new StudentGradesNotFoundException("No grades found for studentCode " + studentCode);
            }
            return grades;
    }

    public Catalogue getCatalogueForStudent(String studentCode) {
        return studentRepository.findCatalogueByStudentCode(studentCode)
                .orElseThrow(() -> new StudentCatalogueNotFoundException("No catalogue found for student code: " + studentCode));
    }
}
