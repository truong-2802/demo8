package com.example.demo5.service.impl;

import com.example.demo5.dto.StudentDto;
import com.example.demo5.entity.Student;
import com.example.demo5.mapper.StudentMapper;
import com.example.demo5.mapper.impl.StudentMapperImpl;
import com.example.demo5.repository.StudentRepository;
import com.example.demo5.repository.StudentRepositoryReflect;
import com.example.demo5.repository.impl.StudentRepositoryImpl;
import com.example.demo5.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service

public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository = new StudentRepositoryImpl();
    StudentMapper studentMapper = new StudentMapperImpl();
    StudentRepositoryReflect studentRepository1 = new StudentRepositoryReflect(Student.class);

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository1.findAll();
        return students.stream()
                .map(studentMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Optional<List<Student>> optionalStudents = studentRepository.getById(String.valueOf(id));
        return optionalStudents.map(students -> {
            if (!students.isEmpty()) {
                return studentMapper.entityToDto(students.get(0));
            }
            return null;
        }).orElse(null);
    }

    @Override
    public List<StudentDto> getByFirstName(String firstName) {
        Optional<List<Student>> optionalStudents = studentRepository.getByFirstName(firstName);
        return optionalStudents.map(students -> students.stream()
                        .map(studentMapper::entityToDto)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public String addStudent(StudentDto studentDto) {
        if (studentDto.getAge() <= 0) {
            return "Age must be greater than 0";
        }
        if (!StringUtils.hasText(studentDto.getFirst_name()) || !StringUtils.hasText(studentDto.getLast_name())) {
            return "First name and Last name are required";
        }
        Student student = studentMapper.dtoToEntity(studentDto);
        studentRepository.save(student);
        return "Success";
    }

    @Override
    public void updateStudent(Long id, StudentDto studentDto) {
        Optional<List<Student>> optionalStudents = studentRepository.getById(String.valueOf(id));
        optionalStudents.ifPresent(students -> {
            if (!students.isEmpty()) {
                Student existingStudent = students.get(0);
                studentMapper.updateStudentFromDto(existingStudent, studentDto);
                studentRepository.save(existingStudent);
            }
        });
    }

    @Override
    public String deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }

}
