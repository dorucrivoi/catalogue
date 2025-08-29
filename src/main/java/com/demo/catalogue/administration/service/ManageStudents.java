package com.demo.catalogue.administration.service;

import com.demo.catalogue.model.student.entity.Student;
import com.demo.catalogue.model.student.service.StudentService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ManageStudents {

    private final StudentService studentService;

    public ManageStudents(StudentService studentService) {
        this.studentService = studentService;
    }

    @Transactional
    public Student createStudent(Student student) {
        return studentService.create(student);
    }

    public List<Student> findStudents() {
        return studentService.getAll();
    }

    public Student findStudent(Long id) {
        return studentService.getById(id);
    }

    @Transactional
    public Student updateStudent(Long id, Student updatedStudent) {
        return studentService.update(id, updatedStudent);
    }

    @Transactional
    public void removeStudent(Long id) {
        studentService.delete(id);
    }
}
