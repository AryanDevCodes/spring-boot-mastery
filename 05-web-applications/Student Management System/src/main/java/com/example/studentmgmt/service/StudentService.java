package com.example.studentmgmt.service;


import com.example.studentmgmt.model.StudentModel;
import com.example.studentmgmt.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @PostConstruct
    public void init() {
        studentRepo.save(new StudentModel(null, "Aryan", "aryan@code.com", "JAVA", 20));
        studentRepo.save(new StudentModel(null, "Alex", "alex@code.com", "PYTHON", 22));
    }

    public Object getAllStudents() {
        return studentRepo.findAll();
    }

    public Optional<StudentModel> getStudentById(Long id) {
        return studentRepo.findById(id);
    }
    public void saveStudent(StudentModel studentModel) {
        studentRepo.save(studentModel);
    }
    public void deleteStudentById(Long id) {
        studentRepo.deleteById(id);
    }

    public void updateStudent(StudentModel studentModel, Long id) {
        Optional<StudentModel> upStudent = studentRepo.findById(id);
        if(upStudent.isPresent()) {
            StudentModel existingStudent = upStudent.get();
            existingStudent.setName(studentModel.getName());
            existingStudent.setEmail(studentModel.getEmail());
            existingStudent.setCourse(studentModel.getCourse());
            existingStudent.setAge(studentModel.getAge());
            studentRepo.save(existingStudent);
        }
    }

}
