package taskpilot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/taskpilotdb";
    private static final String USUARIO = "root";
    private static final String SENHA = "@Luiz09072009";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
