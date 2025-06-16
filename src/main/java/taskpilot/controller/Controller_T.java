package taskpilot.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import taskpilot.dao.tasks_data_manipulation.DBActions_T;
import taskpilot.model.Task;

public class Controller_T extends HttpServlet {
   private static final DBActions_T DBAction = new DBActions_T();

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String screen = request.getServletPath();
      HttpSession session = request.getSession();
      
      if (session != null) {
         session.setMaxInactiveInterval(40 * 3600); 
      } else {
         response.sendRedirect("/taskpilot/login");
      }
      
      switch (screen) {
         case "/tasks" -> request.getRequestDispatcher("/WEB-INF/views/tasks.jsp").forward(request, response);
         default -> {
         }
      }
   }
   @SuppressWarnings("static-access")
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      
      String action = request.getParameter("action");
      String name = request.getParameter("name");
      String description = request.getParameter("description");
      String term = request.getParameter("term");  
      String status = request.getParameter("status");
      String task_ID = request.getParameter("taskID");
      String jsonList = request.getParameter("list");
      HttpSession session = request.getSession();
      /*
      Gson gson = new Gson();
      HashMap<String, String> list = gson.fromJson(jsonList, new TypeToken<HashMap<String, String>>(){}.getType());
      */

      
      if (session != null) {
         switch (action) {
            case "add_task" -> {

               if (DBAction.CREATE(name, description, term, status, request)){
                  String userID_STR = (String) session.getAttribute("current_user_id");
                  int userID = Integer.parseInt(userID_STR);
                  
                  resetList(request, response, session, userID);
               } else {
                  response.sendRedirect("/taskpilot/tasks?message=task20%creation20%NOT20%SUCCESSFUL");
               }
            }

            case "change-task__data" -> {
               
               if(DBAction.UPDATE(name, description, term, status, null, Integer.parseInt(task_ID), request)){
                  String userID_STR = (String) session.getAttribute("current_user_id");
                  int userID = Integer.parseInt(userID_STR);

                  resetList(request, response, session, userID);
               } else {
                  response.sendRedirect("/taskpilot/tasks?message=task20%editing20%NOT20%SUCCESSFUL");
               }
            }
         
            default -> {
            }
         }
      } else {
         response.sendRedirect("/taskpilot/login");
      }
   }

   private void resetList(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    HttpSession session, 
                                    int userID) throws IOException {
      ArrayList<Task> list;
      
      try {
            list = DBAction.SELECT(request, userID);
            session.setAttribute("taskList", list);                  
            response.sendRedirect("/taskpilot/tasks?message=task20%creation20%SUCCESSFUL");

         } catch (ServletException | IOException | SQLException e) {                  
            response.sendRedirect("/taskpilot/tasks?message=Oops!20%something20%went20%wrong;");
            e.printStackTrace();
         }

   }
}
