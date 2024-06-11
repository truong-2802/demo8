package com.example.demo5.mapper.impl;

import com.example.demo5.dto.StudentDto;
import com.example.demo5.entity.Student;
import com.example.demo5.mapper.StudentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student dtoToEntity(StudentDto dto) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        BeanUtils.copyProperties(dto, student);
        return student;
    }

    @Override
    public StudentDto entityToDto(Student entity) {
        if (entity == null) {
            return null;
        }
        StudentDto dto = new StudentDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public void updateStudentFromDto(Student student, StudentDto dto) {
        if (dto != null) {
            student.setFirst_name(dto.getFirst_name());
            student.setLast_name(dto.getLast_name());
            student.setAddress(dto.getAddress());
            student.setAge(dto.getAge());

        }
    }

    @Override
    public void deleteStudentFromDto(Student student, StudentDto dto) {
        if (dto != null) {
            student.setId(dto.getId());
        }
    }
}
