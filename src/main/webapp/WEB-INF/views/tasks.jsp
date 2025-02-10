<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Inter:wght@400&family=Poppins:wght@400&display=swap" rel="stylesheet">
      <link href="/taskpilot/assets/css/style-tasks.css" rel="stylesheet">
      <title>Tasks</title>
   </head>
   <body>
      <div class="container">
         <!-- Barra superior fixa -->
         <div class="top-bar">
            <div>Hello, ${sessionScope.current_username}</div>
            <button class="logout-btn" title="Logout">⎋</button>
         </div>

         <header>
            <h1>Tasks</h1>
            <div class="icons">
               <button title="Add Task" class="top-btns" id="btn-plus">+</button>
               <button title="Statistics" class="top-btns">&#128202;</button>
               <button title="Settings" class="top-btns">&#9881;</button>
            </div>
         </header>

         <div class="form-background invisible" id="form-background">
            <form action="/taskpilot/Controller_T" class="form-addTask">
               <div class="form-container">
                  <input type="hidden" name="action" value="add_task">

                  <div class="btn-remove-container">
                     <div class="fa fa-remove fa-2x btn-remove" id="btn-remove-form"></div>
                     <p class="form-upper-text">Add a new task</p>
                  </div>
                  
                  <div class="input-label">
                     <label for="name">Name:</label>
                     <input type="text" name="name" required>
                  </div>
                  <div class="input-label">
                     <label for="description">Description:</label>
                     <input type="text" name="description">
                  </div>
                  <div class="input-label">
                     <label for="term">Deadline:</label>
                     <input type="date" name="term">
                  </div>

                  <div class="btn">
                     <button type="submit" id="submit-button">Add Task</button>
                  </div>
                  
               </div>
            </form>
         </div>

         <div class="task-list">
            
         </div>
      </div>
      <script src="/taskpilot/assets/js/script-task.js"></script>
   </body>
</html>

