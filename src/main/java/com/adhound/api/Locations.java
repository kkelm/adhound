package com.adhound.api;

import com.adhound.entity.Location;
import com.adhound.entity.User;
import com.adhound.persistence.LocationData;
import com.adhound.persistence.UserData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.Set;

/**
 * API endpoints for locations.
 */
@Path("/locations")
public class Locations {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The User data.
     */
    UserData userData = new UserData();
    /**
     * The Location object.
     */
    Location location = new Location();
    /**
     * The Location data.
     */
    LocationData locationData = new LocationData();

    /**
     * Gets all of the locations related to a specific user.
     *
     * @param username the username
     * @return JSON string of locations data
     */
    @GET
    @Path("/{username}")
    @Produces("application/json")
    public Response getLocations (@PathParam("username") String username) {

        String json = "";
        int statusCode = 200;

        try {

            int userId = userData.authentication.userAuthentication(username);

            User user = (User) userData.crud.getById(userId);
            Set<Location> userLocations = location.getLocations(user);
/*
        LocationData locationData = new LocationData();
        Location location = new Location();
        location.setUser(user);

        Location locations = (Location) locationData.crud.getAll();
        Set<Location> l = location.getLocations();
*/
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            json = mapper.writeValueAsString(userLocations);
        }
        catch (JsonProcessingException jsonException) {
            logger.error(jsonException.getMessage());
            statusCode = 500;
        }

        return Response.status(statusCode).entity(json).build();
    }

    /**
     * Gets a location related to a specific user.
     *
     * @param username the logged in user's username
     * @param id       the id of the location
     * @return JSON string of location data
     */
    @GET
    @Path("/{username}/location/{id}")
    @Produces("application/json")
    public Response getLocation (@PathParam("username") String username, @PathParam("id") int id) {

        String json = "";
        int statusCode = 200;

        try {
            int userId = userData.authentication.userAuthentication(username);
            User user = (User) userData.crud.getById(userId);
            Set<Location> userLocations = location.getLocations(user);

            Iterator locations = userLocations.iterator();

            locationData = new LocationData();
            //Location location = null;

            while(locations.hasNext()) {
                Location currentLocation = (Location) locations.next();
                if (currentLocation.getId() == id) {
                    location = (Location) locationData.crud.getById(id);
                    break;
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            json = mapper.writeValueAsString(location);
        }
        catch (JsonProcessingException jsonException) {
            logger.error(jsonException.getMessage());
            statusCode = 500;
        }

        return Response.status(statusCode).entity(json).build();

    }

    /**
     * Updates a location related to a specific user.
     *
     * @param username the logged in user's username
     * @param location the id of the location
     * @return JSON string of location data
     */
    @PUT
    @Path("/{username}/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putLocation (@PathParam("username") String username, Location location) {

        String json = "";
        int statusCode = 200;

        try {
            int userId = userData.authentication.userAuthentication(username);
            User user = (User) userData.crud.getById(userId);
            Set<Location> userLocations = location.getLocations(user);

            Iterator locations = userLocations.iterator();

            locationData = new LocationData();

            while(locations.hasNext()) {
                Location currentLocation = (Location) locations.next();
                if (currentLocation.getId() == location.getId()) {
                    locationData.crud.updateRecords(location);
                    location = (Location) locationData.crud.getById(location.getId());
                    break;
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            json = mapper.writeValueAsString(location);
        }
        catch (JsonProcessingException jsonException) {
            logger.error(jsonException.getMessage());
            statusCode = 500;
        }

        return Response.status(statusCode).entity(json).build();

    }

    /**
     * Updates a location related to a specific user.
     *
     * @param username the logged in user's username
     * @param id       the id of the location
     * @return JSON string of location data
     */
    @DELETE
    @Path("/{username}/location/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLocation (@PathParam("username") String username, @PathParam("id") int id) {

        String json = "";
        int statusCode = 200;


        try {
            int userId = userData.authentication.userAuthentication(username);
            User user = (User) userData.crud.getById(userId);
            Set<Location> userLocations = location.getLocations(user);

            locationData = new LocationData();

            Location location  = (Location) locationData.crud.getById(id);

            locationData.crud.deleteRecord(location);

            /*
            Iterator locations = userLocations.iterator();

            LocationData locationData = new LocationData();

            while(locations.hasNext()) {
                Location currentLocation = (Location) locations.next();
                if (currentLocation.getId() == location.getId()) {
                    locationData.crud.updateRecords(location);
                    location = (Location) locationData.crud.getById(location.getId());
                    break;
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            json = mapper.writeValueAsString(location);
            */
        }
        catch (HibernateException e) {
            logger.error(e.getMessage());
            statusCode = 500;
        }

        return Response.status(statusCode).entity(json).build();

    }

    /*
    Location Contacts
     */
    /**
     * Gets all of the contacts for a specific location.
     *
     * @param location the location ID
     * @return JSON string of location contacts
     */
    @GET
    @Path("/{location}/contacts")
    @Produces("application/json")
    public Response getLocations (@PathParam("location") int location) {
        String json = "";
        int statusCode = 200;

        try {
            locationData = new LocationData();

            Location locations = (Location) locationData.crud.getById(location);

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            json = mapper.writeValueAsString(locations);
        }
        catch (JsonProcessingException jsonException) {
            logger.error(jsonException.getMessage());
            statusCode = 500;
        }

        return Response.status(statusCode).entity(json).build();
    }

}
