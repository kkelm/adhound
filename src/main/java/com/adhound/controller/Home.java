package com.adhound.controller;

import com.adhound.entity.State;
import com.adhound.entity.User;
import com.adhound.persistence.UserData;
import com.adhound.service.CrudService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        urlPatterns = {"/home"}
)

public class Home extends HttpServlet {

    HttpSession session;

    public CrudService crud;

    UserData userData = new UserData();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        this.crud = new CrudService(User.class);
        List<User> user = this.crud.getAll();
        request.setAttribute("results", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        String searchText = request.getParameter("idTextbox").trim();

        if (!searchText.isEmpty()) {
            int id = Integer.parseInt(searchText);
            //session.setAttribute("title", username);

            /**
             * Instantiate a new array list of user data.
             */
            List<User> user = new ArrayList<>();
            /**
             * Add the user data to the array list.
             */
            user.add((User) userData.crud.getById(id));
            /**
             * Pass the array list to the view.
             */
            request.setAttribute("results", user);
        }
        else {
            session.setAttribute("message", "Please enter a valid ID.");
            request.setAttribute("results", "");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);


    }

}
