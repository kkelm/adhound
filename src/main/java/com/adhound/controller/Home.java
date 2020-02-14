package com.adhound.controller;
import com.adhound.persistence.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/"}
)

public class Home extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Database database = new Database();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");

        dispatcher.forward(request, response);

    }

}