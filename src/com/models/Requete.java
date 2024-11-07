package com.models;

import java.sql.*;
public class Requete {
    //Attribut paramètre BDD
    static final String DB_URL =
            "jdbc:mysql://localhost:3306/java?serverTimezone=UTC";
    static final String USERNAME = "facu";
    static final String PASSWORD = "123456";
    //Connexion à la BDD
    private static Connection connexion;
    static {
        try {
            connexion = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnexion() {
        return connexion;
    }
}

