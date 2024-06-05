package com.example.demo5.util;
import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    public static synchronized Connection getConnection() {
        if (connection == null) {
            init();
        }
        return connection;
    }
    public static void init() {
        Connection newConnection = null;
        try{
            Class.forName(DRIVER_CLASS_NAME);
            newConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            if (newConnection != null) {
                System.out.println("Connection successfully !");
            }else {
                System.err.println("Connection fail !");
            }
        }catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        connection = newConnection;
    }
}
