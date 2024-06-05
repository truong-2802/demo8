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
    StudentService studentService = new StudentServiceImpl();
    // api/v1/student/1  .. and httpMethod  = GET
//    @GetMapping("/student/{id}")
//    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
//        StudentDto studentDto = studentService.getStudentById(id);
//        if (studentDto != null) {
//            return ResponseEntity.ok(studentDto);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @GetMapping(value = "/student/{studentId}")
    public StudentDto get(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }
    @GetMapping(value = "/student")
    public List<StudentDto> finadall(){
        return studentService.findAll();
    }
    @GetMapping(value = "/student/search")
    public List<StudentDto> getByFirstName(@RequestParam("firstName") String firstName){
        return studentService.getByFirstName(firstName);
    }
    @PostMapping(value = "/student/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody StudentDto studentDto) {
        String result = studentService.addStudent(studentDto);
        if ("Success".equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }


}
