package taskpilot.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Controller_T extends HttpServlet {
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String screen = request.getServletPath();
      
      switch (screen) {
         case "/tasks" -> request.getRequestDispatcher("/WEB-INF/views/tasks.jsp").forward(request, response);
         
         default -> {
         }
      }
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String action = request.getParameter("action");
      String name = request.getParameter("name");
      String description = request.getParameter("description");
      String term = request.getParameter("term");  
   }
}
