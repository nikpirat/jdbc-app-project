package com.example.project.repository;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionPool {
    private static final Logger log = Logger.getLogger(String.valueOf(ConnectionPool.class));

    private static final ConnectionPool CONNECTION_POOL = new ConnectionPool();
    private static String URL;
    private static String CLASS_NAME;
    private static String USER;
    private static String PASS;

    private void getResource() throws IOException {
        Properties prop = new Properties();
        String propFileName = "connection.properties";
        prop.load(getClass().getClassLoader().getResourceAsStream(propFileName));
        URL = prop.getProperty("url");
        CLASS_NAME = prop.getProperty("class_name");
        USER = prop.getProperty("user_name");
        PASS = prop.getProperty("password");
    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            CONNECTION_POOL.getResource();
        } catch (IOException e) {
            System.err.println("Cant take to resources");
        }
        try {
            Class.forName(CLASS_NAME);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            log.info("Cant connect to database");
            throw new SQLException("Cant connect to database", e);
        } catch (ClassNotFoundException e) {
            log.info("Driver not found");
            throw new ClassNotFoundException("Driver not found", e);
        }
    }
}
