package com.example.demo5.controller;

import com.example.demo5.dto.StudentDto;
import com.example.demo5.service.StudentService;
import com.example.demo5.service.impl.StudentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{studentId}")
    public StudentDto getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/student")
    public List<StudentDto> findAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/student/search")
    public List<StudentDto> getByFirstName(@RequestParam("firstName") String firstName) {
        return studentService.getByFirstName(firstName);
    }

    @PostMapping("/student/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody StudentDto studentDto) {
//        System.out.println("Received student data: " + studentDto);
        String result = studentService.addStudent(studentDto);
        if ("Success".equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/student/updateStudent/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok("Student updated successfully");
    }

    @DeleteMapping("/student/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}