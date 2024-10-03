package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
     public static Connection connect() {
        Connection connection = null;
        try {
            // Conectando ao banco de dados MySQL
            String url = "jdbc:postgresql://localhost:5432/produtos";
            String user = "postgres";
            String password = "postgres";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return connection;
    }
}
