package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Dados da conexão com PostgreSQL
    private static final String URL = "jdbc:postgresql://192.168.189.128/exemplo";
    private static final String USER = "douglas";
    private static final String PASSWORD = "123456";

    // Retorna uma conexão ativa
    public static Connection getConnection() {
        try {
            // Carrega o driver (opcional nas versões recentes)
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do PostgreSQL não encontrado!");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados PostgreSQL!");
            throw new RuntimeException(e);
        }
    }

    // Fecha a conexão (opcional)
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão!");
        }
    }
}
