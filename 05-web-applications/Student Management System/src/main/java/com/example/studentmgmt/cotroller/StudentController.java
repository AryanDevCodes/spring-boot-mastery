package com.example.studentmgmt.cotroller;

import com.example.studentmgmt.model.StudentModel;
import com.example.studentmgmt.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/form")
    public String addStudent(Model model){
        model.addAttribute("student", new StudentModel());
        return "add-student";
    }

    @PostMapping("/add")
    public String saveStudents(@ModelAttribute StudentModel studentModel) {
        studentService.saveStudent(studentModel);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Optional<StudentModel> model1 = studentService.getStudentById(id);
        model1.ifPresent(student -> model.addAttribute("student", student));
        return "edit-form";
    }

    @PostMapping("/edit")
    public String editStudent(@ModelAttribute StudentModel studentModel) {
        studentService.updateStudent(studentModel,studentModel.getId());
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

}
