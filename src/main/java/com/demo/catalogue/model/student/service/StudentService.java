package com.demo.catalogue.model.student.service;

import com.demo.catalogue.model.student.entity.Student;
import com.demo.catalogue.model.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository repo) {
        this.studentRepository = repo;
    }

    public Student create(Student s) {
        return studentRepository.save(s);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public Student update(Long id, Student s) {
        Student existing = getById(id);
        existing.setName(s.getName());
        existing.setCatCode(s.getCatCode());
        existing.setCode(s.getCode());
        return studentRepository.save(existing);
    }

    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }
}
