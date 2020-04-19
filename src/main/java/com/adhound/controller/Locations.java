package com.adhound.controller;

import com.adhound.entity.Location;
import com.adhound.entity.User;
import com.adhound.service.Authentication;
import com.adhound.persistence.LocationData;
import com.adhound.persistence.UserData;
import com.adhound.service.CrudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.server.ServerProperties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Map;
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

        String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(domain + "/adhound/api/locations").path("{username}").resolveTemplate("username", request.getUserPrincipal().getName());

        String json = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();

        Set locations = mapper.readValue(json, Set.class);

        request.setAttribute("locations", locations);

        // plan = mapper.readValue(response, Plan.class);

        /*
        int userId = userData.authentication.userAuthentication(request.getUserPrincipal().getName());

        User user = (User) userData.crud.getById(userId);
        //Set<Location> locations = user.getLocations();
        //request.setAttribute("locations", locations);
        */

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/locations.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

}
