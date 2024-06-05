package com.example.demo5.FindAll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/students";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    public List<Student> findAll(int page, int size) {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Kết nối tới cơ sở dữ liệu
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Tính toán OFFSET
            int offset = page * size;

            // Tạo câu truy vấn SQL với phân trang
            String sql = "SELECT id, first_name, last_name, address, age FROM students LIMIT ? OFFSET ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, offset);

            // Thực thi câu truy vấn
            resultSet = preparedStatement.executeQuery();

            // Lấy dữ liệu từ ResultSet và tạo danh sách sinh viên
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                int age = resultSet.getInt("age");

                Student student = new Student(id, firstName, lastName, address, age);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và các tài nguyên
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return students;
    }
}
