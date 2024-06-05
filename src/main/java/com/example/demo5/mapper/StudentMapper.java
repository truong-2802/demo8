package com.example.demo5.mapper;

import com.example.demo5.dto.StudentDto;
import com.example.demo5.entity.Student;
public interface StudentMapper {
    Student dtoToEntity(StudentDto dto);
    StudentDto entityToDto(Student entity);
}
