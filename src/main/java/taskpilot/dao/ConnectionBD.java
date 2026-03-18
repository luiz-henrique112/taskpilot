package taskpilot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
   private static final String URL = System.getenv("DB_URL");
   private static final String USER = System.getenv("DB_USER");
   private static final String PASSWORD = System.getenv("DB_PASSWORD");

   public static Connection connect() {
      try {
         return DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (SQLException e) {
         e.printStackTrace();
         throw new RuntimeException("Erro ao conectar ao banco de dados", e);
      }
   }
}
