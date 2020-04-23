package com.adhound.controller;

import com.adhound.entity.Location;
import com.adhound.entity.Region;
import com.adhound.entity.State;
import com.adhound.entity.User;
import com.adhound.persistence.LocationData;
import com.adhound.persistence.UserData;
import com.adhound.service.CrudService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

@WebServlet(
        urlPatterns = {"/dashboard/location"}
)

public class ViewLocation extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    HttpSession session;

    public CrudService stateCrud = new CrudService(State.class);
    List<State> states = this.stateCrud.getAll();

    public CrudService regionCrud = new CrudService(Region.class);
    List<Region> regions = this.regionCrud.getAll();

    private static Validator validator;

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
        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        // HashMap for the returned errors
        Map<String, String> errors = new HashMap<>();

        // Get Location data
        LocationData locationData = new LocationData();
        Location location = (Location) locationData.crud.getById(Integer.parseInt(request.getParameter("id")));
        // Set updated Location data
        location.setName(request.getParameter("nameTextbox").trim());
        location.setPhone(request.getParameter("phoneTextbox").trim());
        location.setFax(request.getParameter("faxTextbox").trim());
        location.setAddress(request.getParameter("addresssTextbox").trim());
        location.setCity(request.getParameter("cityTextbox").trim());
        location.setStateId(Integer.parseInt(request.getParameter("stateIdDropdown").trim()));
        location.setZipcode(request.getParameter("zipcodeTextbox").trim());
        location.setRegionId(Integer.parseInt(request.getParameter("regionIdDropdown").trim()));
        // Validate updated Location data
        Set<ConstraintViolation<Location>> constraintViolations = validator.validate(location);

        if (constraintViolations.isEmpty()) {

            String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            Client client = ClientBuilder.newClient();

            WebTarget target = client.target(domain + "/adhound/api/locations")
                    .path("{username}").resolveTemplate("username", request.getUserPrincipal().getName())
                    .path("{update}").resolveTemplate("update", "update");

            ObjectMapper mapper = new ObjectMapper();

            String locationJSON = mapper.writeValueAsString(location);

            Response json = target.request(MediaType.APPLICATION_JSON).put(Entity.json(locationJSON));

            //location = mapper.reader().forType(Location.class).readValue(json);

            location = json.readEntity(Location.class);

            request.setAttribute("locationName", location.getName());
        }
        else {

            Iterator<ConstraintViolation<Location>> errorMessages = constraintViolations.iterator();

            while (errorMessages.hasNext()) {

                ConstraintViolation<Location> next = errorMessages.next();

                String property = next.getPropertyPath().toString();
                String message = next.getMessage();

                errors.put(property, message);
            }

        }

        request.setAttribute("errormessages", errors);

        request.setAttribute("states", states);

        request.setAttribute("regions", regions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/location.jsp");
        dispatcher.forward(request, response);
    }

}
