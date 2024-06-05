package com.example.demo5.FindAll;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        int page = 0; // Trang đầu tiên
        int size = 10; // Kích thước trang là 10

        List<Student> students = studentDAO.findAll(page, size);

        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", First Name: " + student.getFirstName() + ", Last Name: " + student.getLastName() + ", Address: " + student.getAddress() + ", Age: " + student.getAge());
        }
    }
}
