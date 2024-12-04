<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>TaskPilot Sign In</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Inter:wght@400&family=Poppins:wght@400&display=swap" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/style-register.css" rel="stylesheet">
</head>
<body>

    <div class="container">
        <p class="welcome-text">Welcome to</p>
        <form action="${pageContext.request.contextPath}/Controller" method="post">
            <div class="container-center">
                <h1>TaskPilot</h1>
                <input type="hidden" name="action" value="signIn">

                <input type="text" class="input-field" placeholder="E-mail" id="email" required>
                <div class="passwordInputContainer">
                    <input type="password" class="input-field" placeholder="Password" id="password" required>
                    <p class="helper-text">(at least 8 characters)</p>
                </div>
                <input type="text" class="input-field" placeholder="Username" id="username" required>
            </div>
            <div class="buttons">
                <button type="submit" class="button" id="signIn">Sign In</button>
                <a href="${pageContext.request.contextPath}/login" id="loginLink">Do you already have an account?</a>
            </div>
        </form>
        
    </div>

    <% 
        String alert = (String) request.getAttribute("Invalid E-mail");
        if (alert != null){
    %>
        <script>
            alert("<%= alert %>");
        </script>
    <%
        }
    %>
    <script src = "/taskpilot/assets/js/script-login.js"></script>
</body>
</html>