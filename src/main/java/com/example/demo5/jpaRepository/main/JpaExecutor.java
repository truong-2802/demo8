package com.example.demo5.jpaRepository.main;


import java.util.List;
import java.util.Optional;

public interface JpaExecutor<T> {
    List<T> findAll();
    Optional<T> findById(Long id);

    Optional<T> findById(Number id);
    Optional<T> findByFirstName(String first_name);

}