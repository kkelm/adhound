package com.adhound.controller;

import com.adhound.entity.Location;
import com.adhound.entity.User;
import com.adhound.persistence.LocationData;
import com.adhound.persistence.UserData;
import com.adhound.entity.State;
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
import java.util.Set;

@WebServlet(
        urlPatterns = {"/locations"}
)

public class Locations extends HttpServlet {

    HttpSession session;

    public CrudService crud;

    LocationData locationData = new LocationData();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        UserData userData = new UserData();

        User user = (User) userData.crud.getById(2);
        Set<Location> locations = user.getLocations();

        System.out.println(locations);

        //this.crud = new CrudService(Location.class);
        //List<Location> locations = this.crud.getById(2);
        request.setAttribute("locations", locations);



        RequestDispatcher dispatcher = request.getRequestDispatcher("/locations.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

}
