package com.example.demo5.repository;
import com.example.demo5.dto.StudentDto;
import com.example.demo5.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    /**
     *
     * @param id : String
     * @return : list Dto query in table with id  =  ????
     */
    Optional<List<Student>> getById(String id);
    Optional<List<Student>> getByFirstName(String first_name);
//   List<Student> getAll();

    void save(Student student);

    List<Student> findAll();
//    String updateStudent(Long id, StudentDto studentDto); // Thêm phần cập nhật
//    String deleteStudent(Long id); // Thêm phần xóa
//
//    //    public List<Student> findAll() {
//    //        // Truy vấn và trả về danh sách sinh viên từ database
//    //    }
//    void update(Student student);
//
//    void deleteById(Long id);
String updateStudent(Long id, StudentDto studentDto);

    /**
     * Xóa sinh viên khỏi cơ sở dữ liệu.
     *
     * @param id Id của sinh viên cần xóa.
     * @return   Kết quả xóa.
     */
    String deleteStudent(Long id);

    /**
     * Cập nhật thông tin của sinh viên.
     *
     * @param student Thông tin cần cập nhật.
     */
    void update(Student student);

    /**
     * Xóa sinh viên dựa trên id.
     *
     * @param id Id của sinh viên cần xóa.
     */
    void deleteById(Long id);
}
