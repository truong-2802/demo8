package com.example.demo5.service;

import com.example.demo5.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto getStudentById(Long id);
    List<StudentDto> findAll();
    List<StudentDto> getByFirstName(String firstName);
    String addStudent(StudentDto studentDto);
    void updateStudent(Long id, StudentDto studentDto);
    String deleteStudent(Long id);
}
