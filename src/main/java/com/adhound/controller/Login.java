package com.adhound.controller;

import com.adhound.persistence.UserData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/login"}
)

public class Login extends HttpServlet {

    HttpSession session;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        //UserData userData = new UserData();
        //request.setAttribute("results", userData.getAllUsers(""));

        //session.setAttribute("title", "All Users");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        UserData userData = new UserData();

        String username = request.getParameter("usernameTextbox").trim();
        String password = request.getParameter("passwordTextbox").trim();

        if (!username.trim().isEmpty() && !password.trim().isEmpty()) {
            //session.setAttribute("title", username);
            request.setAttribute("results", userData.getUserData(username, password));
        }
        else {
            session.setAttribute("message", "Please enter a valid username and password.");
            request.setAttribute("results", "");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);


    }

}
