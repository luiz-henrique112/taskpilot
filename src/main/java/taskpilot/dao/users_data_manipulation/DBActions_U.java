package taskpilot.dao.users_data_manipulation;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import taskpilot.dao.ConnectionBD;

public class DBActions_U {

   public static boolean DBConnection(String action, String command, String email, String username, String password, HttpServletRequest request) throws ServletException, IOException {
      try (PreparedStatement stmt = ConnectionBD.connect().prepareStatement(command)) {
         switch (action) {
            case "CREATE" -> {
               stmt.setString(1, email);
               stmt.setString(2, password);
               stmt.setString(3, username);
               stmt.executeUpdate();
            }

            case "setCurrentUser" -> {
               stmt.setString(1, username);
               ResultSet rs = stmt.executeQuery();
               
               while (rs.next()) {
                  int id = rs.getInt("id");
                  String usernameDB = rs.getString("username");
                  
                  HttpSession session = request.getSession();
                  session.setAttribute("current_username", usernameDB);  
                  session.setAttribute("current_user_id", id);
               }
               return true;
            }
            default -> {
            }
            }
      return true;
      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
   }

   public static boolean CREATE(String email, String username, String password, HttpServletRequest request) throws ServletException, IOException {
      String command = "INSERT INTO Users (email, password, username) VALUES (?,?,?);";
      return DBConnection("CREATE", command, email, username, password, request);
   }

   public static boolean DELETE(String email, String username, String password, HttpServletRequest request) throws ServletException, IOException {
      boolean realizedSuccessfully = false;
      return realizedSuccessfully;
   }

   public static boolean UPDATE(String email, String username, String password, HttpServletRequest request) throws ServletException, IOException {
      boolean realizedSuccessfully = false;
      return realizedSuccessfully;
   }

   public static boolean setCurrentUser(String email, String username, String password, HttpServletRequest request) throws ServletException, IOException {
      String command = "SELECT * FROM Users WHERE username = ?;";
      return DBConnection("setCurrentUser", command, email, username, password, request);
   }

}