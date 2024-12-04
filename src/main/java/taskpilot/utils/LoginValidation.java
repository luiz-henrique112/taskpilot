package taskpilot.utils;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginValidation extends HttpServlet {


    protected void validation(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException {
        if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            //DATA VALIDATION
        } else {
            request.setAttribute("Invalid E-mail","Invalid E-mail format. Try again.");
        }
    }
}
