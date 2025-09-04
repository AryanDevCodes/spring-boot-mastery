package com.example.student_mgmt.controller;

import com.example.student_mgmt.service.StudentService;
import com.example.student_mgmt.modal.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:5173") // allow frontend (Vite default port)
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // ✅ GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // ✅ POST add new student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    // ✅ PUT update existing student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updated = service.updateStudent(id, student);
        if (updated == null) {
            return ResponseEntity.notFound().build(); // 404 if not exists
        }
        return ResponseEntity.ok(updated);
    }

    // ✅ DELETE student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean deleted = service.deleteStudent(id);
        if (!deleted) {
            return ResponseEntity.notFound().build(); // 404 if not exists
        }
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
