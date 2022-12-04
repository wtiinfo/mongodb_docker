package com.wtiinfo.service;

import com.wtiinfo.model.Student;
import com.wtiinfo.repository.StudentRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentByEmail(String email) {
        return repository.findStudentByEmail(email).orElseThrow(IllegalStateException::new);
    }

}
