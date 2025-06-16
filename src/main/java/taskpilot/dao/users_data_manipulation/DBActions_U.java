package taskpilot.dao.users_data_manipulation;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import taskpilot.dao.ConnectionBD;
import taskpilot.dao.tasks_data_manipulation.DBActions_T;
import taskpilot.model.Task;

public class DBActions_U {
   public static boolean executeDBCommand(String action, 
                                          String command, 
                                          String email, 
                                          String username, 
                                          String password, 
                                          HttpServletRequest request) throws ServletException, IOException {
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
                  int id_DB = rs.getInt("id");
                  String usernameDB = rs.getString("username");
                  String id = Integer.toString(id_DB);
                  HttpSession session = request.getSession();
                  DBActions_T DBactsT = new DBActions_T();
                  ArrayList<Task> list = DBactsT.SELECT(request, id_DB);

                  session.setAttribute("current_username", usernameDB);  
                  session.setAttribute("current_user_id", id);
                  session.setAttribute("taskList", list); 
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

   public static boolean CREATE(String email,
                              String username, 
                              String password, 
                              HttpServletRequest request) throws ServletException, IOException {
      String command = "INSERT INTO Users (email, password, username) VALUES (?,?,?);";
      String action = "CREATE";
      return executeDBCommand(action, command, email, username, password, request);
      
   }

   public static boolean DELETE(String email, String username, String password, HttpServletRequest request) throws ServletException, IOException {
      boolean realizedSuccessfully = false;
      return realizedSuccessfully;
   }

   public static boolean UPDATE(String email, String username, String password, HttpServletRequest request) throws ServletException, IOException {
      boolean realizedSuccessfully = false;
      return realizedSuccessfully;
   }

   public static boolean setCurrentUser(String email, 
                                       String username, 
                                       String password, 
                                       HttpServletRequest request) throws ServletException, IOException {
      String command = "SELECT * FROM Users WHERE username = ?;";
      String action = "setCurrentUser";
      return executeDBCommand(action, command, email, username, password, request);
   }
}