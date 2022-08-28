package com.config;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConfig {

    private static final String URL = "jdbc:postgresql://localhost:5432/Nix_11";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public JDBCConfig() {
    }

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
