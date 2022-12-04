package com.wtiinfo.controller;

import com.wtiinfo.model.Student;
import com.wtiinfo.service.StudentService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> fetchAllStudents() {
        return  studentService.getAllStudents();
    }

}
