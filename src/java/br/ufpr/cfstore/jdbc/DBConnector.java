/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.cfstore.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta classe faz conexão com o banco de dados.
 * Toda transação deve ser feita por meio de uma instância de DBConnector.
 * @author Regis
 */
public class DBConnector {

    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}
