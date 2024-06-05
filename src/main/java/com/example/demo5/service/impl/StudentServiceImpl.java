package com.example.demo5.service.impl;

import com.example.demo5.dto.StudentDto;
import com.example.demo5.entity.Student;
import com.example.demo5.mapper.StudentMapper;
import com.example.demo5.mapper.impl.StudentMapperImpl;
import com.example.demo5.repository.StudentRepository;
import com.example.demo5.repository.StudentRepositoryReflect;
import com.example.demo5.repository.impl.StudentRepositoryImpl;
import com.example.demo5.service.StudentService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository = new StudentRepositoryImpl();
    StudentMapper studentMapper = new StudentMapperImpl();
    StudentRepositoryReflect studentRepository1 = new StudentRepositoryReflect(Student.class);



    public List<StudentDto> findAll() {
        List<Student> students = studentRepository1.findAll();
//        // mapping list entity -> dto
//        List<StudentDto> studentDtos = new ArrayList<>();
//        for(Student s : students){
//            StudentDto dto  = studentMapper.entityToDto(s);
//            studentDtos.add(dto);
//        }
//        return studentDtos;
        // lambda function
        return students.stream()
                .map(studentMapper::entityToDto)
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        List<String> strs = Arrays.asList("sfdsfd","sfsfsd","sgsdgdsg");
//        for(String s : strs){
//            System.err.println(s);
//        }
        // -> lambda
        strs.forEach(System.err::println);


        List<Student> students = new ArrayList<>();
        // get all name of student -> to list <String>

        List<String> strName = new ArrayList<>();
        for (Student s : students){
            strName.add(s.getFirstName());
        }
        List<String> strName1 = students.stream().map(Student::getFirstName).collect(Collectors.toList());





    }


//    @Override
//    public StudentDto getById(Long id) {
//        Optional<List<Student>> optionalStudents = studentRepository.getById(String.valueOf(id));
//        if (optionalStudents.isPresent()) {
//            List<Student> students = optionalStudents.get();
//            if (!CollectionUtils.isEmpty(students)) {
//                return studentMapper.entityToDto(students.get(0));
//            }
//        }
//        return null;
//    }



    @Override
    public StudentDto getStudentById(Long id) {
        Optional<List<Student>> optionalStudents = studentRepository.getById(String.valueOf(id));
        if (optionalStudents.isPresent()) {
            List<Student> students = optionalStudents.get();
            if (!CollectionUtils.isEmpty(students)) {
                return studentMapper.entityToDto(students.get(0));
            }
        }
        return null;

    }


    @Override
    public List<StudentDto> getByFirstName(String firstName) {
        Optional<List<Student>> optionalStudents = studentRepository.getByFirstName(firstName);
        if (optionalStudents.isPresent()) {
            List<Student> students = optionalStudents.get();
            if (!CollectionUtils.isEmpty(students)) {
                return students.stream()
                        .map(studentMapper::entityToDto)
                        .collect(Collectors.toList());
            }
        }
        return null;
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
}

