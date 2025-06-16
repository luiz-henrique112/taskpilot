package taskpilot.dao.tasks_data_manipulation;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import taskpilot.dao.ConnectionBD;
import taskpilot.model.Task;

public class DBActions_T {

   public static ArrayList<Task> list = new ArrayList<>();
   public static HashMap<String, String> dataList = new HashMap<>();


   private static boolean executeDBCommand(String action, 
                                          String command, 
                                          String name, 
                                          String description, 
                                          String term, 
                                          String status,
                                          int ID, 
                                          HttpServletRequest request) throws ServletException, IOException {
      try (PreparedStatement stmt = ConnectionBD.connect().prepareStatement(command)) {
         HttpSession session = request.getSession();

         switch (action) {
         case "CREATE" -> {
            String userID_STR = (String) session.getAttribute("current_user_id");
            int userID = Integer.parseInt(userID_STR);

            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, term);
            stmt.setInt(4, userID);
            stmt.executeUpdate();
         }
         
         case "SELECT" -> {
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();

            list.clear();
            while (rs.next()) {
               int taskID = rs.getInt("taskID");
               String nameDB = rs.getString("name");
               String descriptionDB = rs.getString("description");
               String termDB = rs.getString("term");
               String statusDB = rs.getString("status");

               list.add(new Task(taskID, nameDB, descriptionDB, termDB, statusDB));
            }  
         }

         case "UPDATE" -> {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, term);
            stmt.setString(4, status);
            stmt.setInt(5, ID);
            stmt.executeUpdate();
         }
         
         default -> {
            }
         } 
         return true;
      } catch (SQLException e) {
         return false;
      }
   }

   public static boolean CREATE(String name, 
                              String description, 
                              String term, 
                              String status,
                              HttpServletRequest request) throws ServletException, IOException {
      String command = "INSERT INTO Tasks (name, description, term, userID) VALUES (?,?,?,?);";
      String action = "CREATE";
      
      return executeDBCommand(action, command, name, description, term, status, 0, request);
   }

    

   public ArrayList<Task> SELECT(HttpServletRequest request,  int user_ID) throws ServletException, IOException, SQLException{
      String command = "SELECT * FROM Tasks WHERE UserID = ?";
      String action = "SELECT";

      return executeDBCommand(action, command, "", "", "", "", user_ID, request) ? list : null;
   }
   
   public static boolean UPDATE(String name, 
                              String description, 
                              String term, 
                              String status,
                              HashMap<String, String> list,
                              int taskID,
                              HttpServletRequest request
                              ) throws ServletException, IOException{

      String command = "UPDATE Tasks SET name = ?, description = ?, term = ?, status = ? WHERE taskID = ?";
      String action = "UPDATE";

      return executeDBCommand(action, command, name, description, term, status, taskID, request);
   }
   
   public static boolean DELETE(){
      return true;
   }
}
