package com.example.demo5.entity;

import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.example.demo5.jpaRepository.annotation.Id;
import com.example.demo5.jpaRepository.annotation.Column;
import com.example.demo5.jpaRepository.annotation.Entity;

@Entity(tableName = "student")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    @Id(name = "id")
    private Long id;
    @Column(columnName = "first_name")
    private String first_name;
    @Column(columnName = "last_name")
    private String last_name;
    @Column(columnName = "address")
    private String address;
    @Column(columnName = "age")
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

//    public String getFirstName() {
//        return "Student{" +
//                "id=" + id +
//                ", firstName='" + first_name + '\'' +
//                ", lastName='" + last_name + '\'' +
//                ", address='" + address + '\'' +
//                ", age=" + age +
//                '}';
//    }
}
