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

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.Set;

@Path("/locations")
public class Locations {

    UserData userData = new UserData();

    @GET
    @Path("/{username}")
    @Produces("application/json")
    public Response getLocations (@PathParam("username") String username) throws JsonProcessingException {



        int userId = userData.authentication.userAuthentication(username);

        User user = (User) userData.crud.getById(userId);
        Set<Location> locations = user.getLocations();
/*
        LocationData locationData = new LocationData();
        Location location = new Location();
        location.setUser(user);

        Location locations = (Location) locationData.crud.getAll();
        Set<Location> l = location.getLocations();
*/

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = mapper.writeValueAsString(locations);


        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("/{username}/location/{id}")
    @Produces("application/json")
    public Response getLocation (@PathParam("username") String username, @PathParam("id") int id) throws JsonProcessingException {

        int userId = userData.authentication.userAuthentication(username);
        User user = (User) userData.crud.getById(userId);
        Set<Location> userLocations = user.getLocations();

        Iterator locations = userLocations.iterator();

        LocationData locationData = new LocationData();
        Location location = null;

        while(locations.hasNext()) {
            Location currentLocation = (Location) locations.next();
            if (currentLocation.getId() == id) {
                location = (Location) locationData.crud.getById(id);
                break;
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = mapper.writeValueAsString(location);

        return Response.status(200).entity(json).build();
    }

    @PUT
    @Path("/{username}/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putLocation (@PathParam("username") String username, Location location) throws JsonProcessingException {

        int userId = userData.authentication.userAuthentication(username);
        User user = (User) userData.crud.getById(userId);
        Set<Location> userLocations = user.getLocations();

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

        String json = mapper.writeValueAsString(location);

        return Response.status(200).entity(json).build();
    }
}
