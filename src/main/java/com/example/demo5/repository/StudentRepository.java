package com.example.demo5.repository;
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
}
