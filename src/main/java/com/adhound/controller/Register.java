package com.adhound.controller;

import com.adhound.entity.State;
import com.adhound.entity.User;
import com.adhound.entity.UserRole;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(
        urlPatterns = {"/register"}
)

public class Register extends HttpServlet {

    HttpSession session;

    public CrudService crud;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        this.crud = new CrudService(State.class);
        //State state = (State)
        List <State> states = this.crud.getAll();

        request.setAttribute("states", states);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        UserData userData = new UserData();

        User newUser = new User();

        newUser.setUsername(request.getParameter("emailTextbox").trim());
        newUser.setPassword(request.getParameter("passwordTextbox").trim());
        newUser.setFirstName(request.getParameter("firstNameTextbox").trim());
        newUser.setLastName(request.getParameter("lastNameTextbox").trim());
        newUser.setPhone(request.getParameter("phoneTextbox").trim());
        newUser.setFax(request.getParameter("faxTextbox").trim());
        newUser.setEmail(request.getParameter("emailTextbox").trim());
        newUser.setAddress(request.getParameter("addresssTextbox").trim());
        newUser.setCity(request.getParameter("cityTextbox").trim());
        newUser.setStateId(Integer.parseInt(request.getParameter("stateIdDropdown").trim()));
        newUser.setZipcode(request.getParameter("zipcodeTextbox").trim());

        int newId = (int) userData.crud.insertRecord(newUser);

        UserRole userRole = new UserRole(newUser.getUsername());
        Serializable userRoleId = userData.crud.insertRecord(userRole);

        User insertedUser = (User) userData.crud.getById(newId);

        if (insertedUser != null) {
            /**
             * Instantiate a new array list of user data.
             */
            List <User> user = new ArrayList<>();
            /**
             * Add the user data to the array list.
             */
            user.add(insertedUser);
            /**
             * Pass the array list to the view.
             */
            request.setAttribute("results", user);

        }
        else {
            session.setAttribute("message", "Please enter a valid username and password.");
            request.setAttribute("results", "");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);


    }

}
