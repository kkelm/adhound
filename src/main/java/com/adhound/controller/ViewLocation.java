package com.adhound.controller;

import com.adhound.entity.Location;
import com.adhound.entity.Region;
import com.adhound.entity.State;
import com.adhound.persistence.LocationData;
import com.adhound.persistence.UserData;
import com.adhound.service.CrudService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import java.util.List;
import java.util.Set;

@WebServlet(
        urlPatterns = {"/dashboard/location"}
)

public class ViewLocation extends HttpServlet {

    HttpSession session;

    public CrudService stateCrud = new CrudService(State.class);
    List<State> states = this.stateCrud.getAll();

    public CrudService regionCrud = new CrudService(Region.class);
    List<Region> regions = this.regionCrud.getAll();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(domain + "/adhound/api/locations")
                .path("{username}").resolveTemplate("username", request.getUserPrincipal().getName())
                .path("{location}").resolveTemplate("location", "location")
                .path("{id}").resolveTemplate("id", request.getParameter("id"));

        String json = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Location location = mapper.reader().forType(Location.class).readValue(json);

        request.setAttribute("location", location);

        request.setAttribute("states", states);

        request.setAttribute("regions", regions);

        // plan = mapper.readValue(response, Plan.class);

        /*
        int userId = userData.authentication.userAuthentication(request.getUserPrincipal().getName());

        User user = (User) userData.crud.getById(userId);
        //Set<Location> locations = user.getLocations();
        //request.setAttribute("locations", locations);
        */

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/location.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

}
