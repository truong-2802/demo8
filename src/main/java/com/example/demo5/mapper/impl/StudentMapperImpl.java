package com.example.demo5.mapper.impl;

import com.example.demo5.dto.StudentDto;
import com.example.demo5.entity.Student;
import com.example.demo5.mapper.StudentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

public class StudentMapperImpl implements StudentMapper {
    @Override
    public  Student dtoToEntity(StudentDto dto) {
        Student student = new Student();
        BeanUtils.copyProperties(dto,student);
        return student;
    }
    @Override
    public StudentDto entityToDto(Student entity) {
        StudentDto dto = new StudentDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }
}
