package taskpilot.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import taskpilot.dao.DBManipulation;
import taskpilot.dao.DataDBValidation;
import taskpilot.utils.DataFormatValidation;


public class Controller extends HttpServlet {  
    public static DataFormatValidation dataValidation = new DataFormatValidation();
    public static DataDBValidation DBvalidation = new DataDBValidation();
    public static DBManipulation DBactions = new DBManipulation();

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

    @SuppressWarnings("static-access")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");  

        switch (action) {
            case "signIn":
                if (dataValidation.validation(email, username, password)) {
                    if (DBvalidation.valid(email, username, password, action)) {
                        if (DBactions.CREATE(email, username, password)) {
                            if (DBactions.setCurrentUser(email, username, password)) {
                                response.sendRedirect("/taskpilot/dashboard");
                            } else {
                                response.sendRedirect("taskkpilot/signIn?error=" + java.net.URLEncoder.encode("An unexpected error occurred while processing the request. Please try again later.", "UTF-8"));
                            }
                        } else {
                            response.sendRedirect("/taskpilot/signIn?error=" + java.net.URLEncoder.encode("Your data could not be registered. Try again later.", "UTF-8"));
                        }

                    } else {
                        response.sendRedirect("/taskpilot/signIn?error=" + java.net.URLEncoder.encode("Users with this E-mail or username already registered.", "UTF-8"));
                    }
                } else {
                    response.sendRedirect("/taskpilot/signIn?error=" + java.net.URLEncoder.encode("Make sure the email is in a valid format, the username is at least 4 characters long, and the password is at least 8 characters long.", "UTF-8"));                
                }
                break;
            case "login":
                if (dataValidation.validation(email, username, password)) {
                    if (DBvalidation.valid(email, username, password, action)) {
                        DBactions.setCurrentUser(email, username, password);
                        response.sendRedirect("/taskpilot/dashboard");
                    } else {
                        response.sendRedirect("/taskpilot/login?error=" + java.net.URLEncoder.encode("No users with this data found.", "UTF-8"));
                    }
                } else {
                    response.sendRedirect("/taskpilot/login?error=" + java.net.URLEncoder.encode("Make sure the email is in a valid format, the username is at least 4 characters long, and the password is at least 8 characters long.", "UTF-8"));
                }
                break;
        
            default:
                break;
        }
    }
}

