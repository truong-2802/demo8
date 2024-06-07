package com.example.demo5.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool instance;
    private List<Connection> connections;
    private static final int POOL_SIZE = 10;

    // db config
    // config value ->
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }
    public synchronized Connection getConnection() {
        if (connections.isEmpty()) {
            return null;
        }
        return connections.remove(connections.size() - 1);

    }
    public ConnectionPool() {
        connections = new ArrayList<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection newConnection = null;
            try {
                Class.forName(DRIVER_CLASS_NAME);
                newConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                connections.add(newConnection);
            }catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage()) ;
            }
            }
        }
    }

