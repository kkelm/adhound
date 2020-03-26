package com.adhound.controller;

import com.adhound.entity.Location;
import com.adhound.entity.User;
import com.adhound.service.Authentication;
import com.adhound.persistence.LocationData;
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
import java.util.Set;

@WebServlet(
        urlPatterns = {"/dashboard/locations"}
)

public class Locations extends HttpServlet {

    HttpSession session;

    public CrudService crud;

    LocationData locationData = new LocationData();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        UserData userData = new UserData();

        int userId = userData.authentication.userAuthentication(request.getUserPrincipal().getName());

        User user = (User) userData.crud.getById(userId);
        Set<Location> locations = user.getLocations();

        request.setAttribute("locations", locations);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/locations.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

}
