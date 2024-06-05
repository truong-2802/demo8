package com.example.demo5.jpaRepository.main.impl;

import com.example.demo5.jpaRepository.main.JpaExecutor;
import com.example.demo5.jpaRepository.consts.SqlQueryConstants ;
import com.example.demo5.jpaRepository.annotation.Entity;
import com.example.demo5.util.ConnectionPool;



import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class JpaExecutorImpl<T> implements JpaExecutor<T> {

    private Class<T> clazz;
    private  String tableName;
    private String className;
    Field[] fields;

    public static void main(String[] args) {

    }

    public JpaExecutorImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.className = clazz.getSimpleName();
        // get mapping of tableName
        this.tableName = clazz.getAnnotation(Entity.class) != null ?
                clazz.getAnnotation(Entity.class).tableName()
                : className;
        this.fields = clazz.getDeclaredFields();
        System.err.println(this.className);
        System.err.println(this.tableName);
        System.err.println(this.fields);
    }
    public abstract List<T> rowMapper(ResultSet rs);


    @Override
    public List<T> findAll() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.getConnection();
        String sql  = SqlQueryConstants.SQL_SELECT_ALL.replace("%table_name%", this.tableName);
        PreparedStatement pt = null;
        try {
            pt = con.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();
            return rowMapper(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Optional<T> findById(Number id){
        return Optional.empty();
    }
    @Override
    public Optional<T> findByFirstName(String first_name){
        return Optional.empty();
    }

}
