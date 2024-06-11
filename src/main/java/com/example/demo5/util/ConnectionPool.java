package com.example.demo5.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool instance;
    private List<Connection> connections;
    private static final int POOL_SIZE = 10;

    // db config
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "admin@123";

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private ConnectionPool() {
        connections = new ArrayList<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Class.forName(DRIVER_CLASS_NAME);
                Connection newConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                connections.add(newConnection);
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println("Exception occurred while creating connection: " + ex.getMessage());
            }
        }
    }

    public synchronized Connection getConnection() {
        if (connections.isEmpty()) {
            return null;
        }
        return connections.remove(connections.size() - 1);
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Failed to release connection: " + e.getMessage());
            }
        }
    }
}
