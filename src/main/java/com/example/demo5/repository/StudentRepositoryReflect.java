package com.example.demo5.repository;

import com.example.demo5.entity.Student;
import com.example.demo5.jpaRepository.main.impl.JpaExecutorImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryReflect  extends JpaExecutorImpl<Student> {
    public StudentRepositoryReflect(Class<Student> clazz) {
        super(clazz);
    }


    public static void main(String[] args) {
        StudentRepositoryReflect repo = new StudentRepositoryReflect(Student.class) {
            @Override
            public Optional<Student> findById(Long id) {
                return Optional.empty();
            }
            public Optional<Student> findByFirstName(String first_name) {
                return Optional.empty();
            }

        };
        repo.findAll();

    }
    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        return students;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }
    @Override
    public Optional<Student> findByFirstName(String first_name) {
        return Optional.empty();
    }

    @Override
    public List<Student> rowMapper(ResultSet rs) {
        List<Student> students = new ArrayList<>();
        try {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getLong("id"));
                s.setFirst_name(rs.getString("first_name"));
                s.setLast_name(rs.getString("last_name"));
                s.setAddress(rs.getString("address"));
                s.setAge(rs.getInt("age"));
                students.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }



}
