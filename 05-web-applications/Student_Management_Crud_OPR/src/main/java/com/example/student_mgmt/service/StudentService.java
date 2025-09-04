package com.example.student_mgmt.service;

import com.example.student_mgmt.modal.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final Map<Long, Student> studentMap = new HashMap<>();
    private Long nextId = 1L;

    // Constructor with dummy students preloaded
    public StudentService() {
        addStudent(new Student(null, "Aryan Raj", "aryan@example.com"));
        addStudent(new Student(null, "Anuj Sharma", "anuj@example.com"));
    }

    // Get all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    // Add a student
    public Student addStudent(Student student) {
        student.setId(nextId++);
        studentMap.put(student.getId(), student);
        return student;
    }

    // Update student by ID
    public Student updateStudent(Long id, Student student) {
        if (studentMap.containsKey(id)) {
            student.setId(id);
            studentMap.put(id, student);
            return student;
        }
        return null;
    }

    // Delete student by ID
    public boolean deleteStudent(Long id) {
        return studentMap.remove(id) != null;
    }
}
