package com.example.demo5.repository.impl;

import com.example.demo5.dto.StudentDto;
import com.example.demo5.entity.Student;
import com.example.demo5.repository.StudentRepository;
import com.example.demo5.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo5.jpaRepository.consts.SqlQueryConstants.SQL_SELECT_ALL;

public class StudentRepositoryImpl implements StudentRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/student";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String SQL_SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE id = ?";
    private static final String SQL_SELECT_STUDENT_BY_FIRST_NAME = "SELECT * FROM student WHERE first_name = ?";

    @Override
    public Optional<List<Student>> getById(String id) {
        List<Student> students = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (Connection con = connectionPool.getConnection();
             PreparedStatement pt = con.prepareStatement(SQL_SELECT_STUDENT_BY_ID)) {
            pt.setString(1, id);
            ResultSet rs = pt.executeQuery();
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
        return Optional.of(students);
    }

    @Override
    public Optional<List<Student>> getByFirstName(String first_name) {
        List<Student> students = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (Connection con = connectionPool.getConnection();
             PreparedStatement pt = con.prepareStatement(SQL_SELECT_STUDENT_BY_FIRST_NAME)) {
            pt.setString(1, first_name);
            ResultSet rs = pt.executeQuery();
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
        return Optional.of(students);
    }

    @Override
    public void save(Student student) {
        String query = "INSERT INTO student(first_name, last_name, address, age) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, student.getFirst_name());
            stmt.setString(2, student.getLast_name());
            stmt.setString(3, student.getAddress());
            stmt.setInt(4, student.getAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (Connection con = connectionPool.getConnection();
             PreparedStatement pt = con.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = pt.executeQuery();
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


    @Override
    public String updateStudent(Long id, StudentDto studentDto) {
        return null;
    }

    @Override
    public String deleteStudent(Long id) {
        return "DELETE FROM student WHERE id = " + id;
    }

    @Override
    public void update(Student student) {

        // Cập nhật thông tin sinh viên trong database
    }

    @Override
    public void deleteById(Long id) {
        // Xóa sinh viên theo id
    }
}
