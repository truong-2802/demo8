package com.example.demo5.repository.impl;

import com.example.demo5.entity.Student;
import com.example.demo5.repository.StudentRepository;
import com.example.demo5.util.ConnectionPool;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/student";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String SQL_SELECT_STUDENT_BY_ID = "select * from student WHERE id = ?";

    public static void main(String[] args){
        StudentRepository studentRepo = new StudentRepositoryImpl();
        Optional<List<Student>> s= studentRepo.getById("'1' or 0 =0 ");
        if(s.isPresent()){
            System.err.println("Student with id 1 : "+ s.get());
        }
        Optional<List<Student>> t = studentRepo.getByFirstName("'1' or 0 =0 ");
        if(s.isPresent()){
            System.err.println("first name : "+ t.get());
        }

    }


    @Override
    public Optional<List<Student>> getById(String id) {
//        Connection connection = DatabaseHelper.getConnection();
        List<Student> students = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.getConnection();
        try{
            PreparedStatement pt = con.prepareStatement(SQL_SELECT_STUDENT_BY_ID);
            pt.setString(1, String.valueOf(id));
            ResultSet rs = pt.executeQuery();
//             Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(SQL_SELECT_STUDENT_BY_ID.replace("?"
//                    ,String.valueOf(id)));
            while(rs.next()){
                Student s = new Student();
                s.setId(rs.getLong("id"));
                s.setFirst_name(rs.getString("first_name"));
                s.setLast_name(rs.getString("last_name"));
                s.setAddress(rs.getString("address"));
                s.setAge(rs.getInt("age"));
                students.add(s);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);

        }
        return Optional.of(students);
    }

    @Override
    public Optional<List<Student>> getByFirstName(String first_name) {
//        public Optional<List<Student>> getByFirstName;(String first_name) {
            List<Student> students = new ArrayList<>();
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection con = connectionPool.getConnection();
            try {
                // Tạo câu truy vấn SQL
                String sql = "SELECT * FROM student WHERE first_name = ?";
                PreparedStatement pt = con.prepareStatement(sql);
                pt.setString(1, first_name);

                // Thực thi câu truy vấn
                ResultSet rs = pt.executeQuery();

                // Xử lý kết quả trả về từ câu truy vấn
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

//    @Override
//    public List<Student> getAll() {
//        return List.of();
//    }

    @Override
    public void save(Student student) {
        String query = "insert into student(first_name,last_name,address,age) values(?,?,?,?)";
        try(Connection con= DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1,student.getFirst_name());
            stmt.setString(2,student.getLast_name());
            stmt.setString(3,student.getAddress());
            stmt.setInt(4,student.getAge());
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }
}
