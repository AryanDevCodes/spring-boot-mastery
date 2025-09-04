package com.example.stmgmt.service;

import com.example.stmgmt.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();
    private long nextId = 1;
    public StudentService() {
        addStudent(new Student(null,"Aryan Raj","arya@9078.com"));
        addStudent(new Student(null,"Aryan Raju","arya@9078raju.com"));
    }

    public Student getStudentById(long id) {
        return studentMap.get(id);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    public void addStudent(Student student) {
        student.setId(nextId++);
        studentMap.put(student.getId(), student);
    }

    public void updateStudent(Student student) {
        Student oldStudent = studentMap.get(student.getId());
        if(oldStudent!=null){
            oldStudent.setName(student.getName());
            oldStudent.setEmail(student.getEmail());
        }
    }

    public void deleteStudent(long id) {
        studentMap.remove(id);
    }
}
