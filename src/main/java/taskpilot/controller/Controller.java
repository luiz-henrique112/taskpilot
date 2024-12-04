package taskpilot.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet {  

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String screen = request.getServletPath();

        switch (screen) {
            case "/login":
                    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                break;
            case "/signIn":
                    request.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(request, response);
                break;
            case "/dashboard":
                    request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
                break;
        
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "signIn":
                response.sendRedirect("/taskpilot/login");
                break;
            case "login":
                response.sendRedirect("/taskpilot/signIn");
                break;
        
            default:
                break;
        }
    }
}
