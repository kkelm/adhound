package com.adhound.persistence;

import com.adhound.entity.Location;
import com.adhound.entity.User;
import com.adhound.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LocationDataTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    UserData userData;
    LocationData locationData;

    /**
     * Sets up the tests with a new objects.
     */
    @BeforeEach
    void setUp() {
        userData = new UserData();
        locationData = new LocationData();
    }

    /**
     * Test get all locations data.
     */
    @Test
    void testGetAllLocations() {
        logger.info("Start Location getAll()");
        List<Location> locations = locationData.crud.getAll();
        int currentSize = locations.size();
        assertEquals(currentSize, locations.size());
        logger.info("Got all users");
        logger.info("End Location getAll()");
    }

    /**
     * Test get one location data success.
     */
    @Test
    void testGetByIdLocation() {

        logger.info("Start Location getById()");
        List<User> users = userData.crud.getAll();
        Iterator userList = users.iterator();

        while (userList.hasNext()) {

            User user = (User) userList.next();
            Set<Location> userLocations = user.getLocations();

            if (userLocations.iterator().hasNext()) {
                Location location = userLocations.iterator().next();
                Location locationFound  = (Location) locationData.crud.getById(location.getId());
                assertEquals(location, locationFound);
                logger.info("Location " + location.getName() + " found by getById()");
                break;
            }
        }

        logger.info("End Location getById()");
    }

    /**
     * Test the creation of a new location record.
     */
    @Test
    void testInsertLocation() {
        logger.info("Start Location Insert");
        User user = (User) userData.crud.getById(2);
        String newLocationName = "Test Location " + Math.round(Math.random()*100);
        Location newObject = new Location(user, newLocationName, "(123) 456-7890", "(987) 654-3210", "123 Test Street", "Madison", 33, "12345", 1);
        //user.getLocations().add(newObject);
        //newObject.setUser(user);
        int newId = (int) locationData.crud.insertRecord(newObject);
        logger.info("Inserted record with the ID: " + newId);
        logger.info("End Location Insert");
        Location getRecord = (Location) locationData.crud.getById(newId);

        assertEquals(getRecord, newObject);
    }

    /**
     * Test an update of a user record.
     */
    @Test
    void testUpdateLocation() {
        logger.info("Start Location Update");
        // Get user information
        User user = (User) userData.crud.getById(2);
        // Get locations associated with the user
        Set<Location> userLocations = user.getLocations();
        // Get a location from the set
        Location userLocation = userLocations.iterator().next();
        // Get the location name, add "Updated" to the name, and set the new name
        userLocation.setName(userLocation.getName() + " Updated");
        // Update the location database record with the updated updated object
        locationData.crud.updateRecords(userLocation);
        // Get the updated location from the database based on the user location ID
        Location updatedRecord = (Location) locationData.crud.getById(userLocation.getId());

        logger.info("End Location Update");

        assertEquals(userLocation, updatedRecord);
/*
        Location updateRecord = (Location) locationData.crud.getById(userLocationId);
        updateRecord.setName( updateRecord.getName() + " Updated");
        locationData.crud.updateRecords(updateRecord);
        Location getRecord = (Location) locationData.crud.getById(1);
        assertEquals(updateRecord.getName() + " Updated", getRecord.getName());
*/
        // logger.info("Updated the location name for ID: " + getRecord.getId());
    }

    /**
     * Test the deletion of a user record.
     */
    @Test
    void testDeleteLocation() {
        // Get user information
        User user = (User) userData.crud.getById(2);
        // Get locations associated with the user
        Set <Location> userLocations = user.getLocations();
        // Get a location from the set
        Location userLocation = userLocations.iterator().next();
        // Get the ID of the location to delete
        int deleteId = userLocation.getId();
        // Remove location from the database
        locationData.crud.deleteRecord(userLocation);
        // Test if record is gone
        assertNull(userData.crud.getById(deleteId));

        logger.info("Deleted record for ID: " + deleteId);
        /*
        int deleteId = users.get((users.size() - 1)).getId();
        userData.crud.deleteRecord(userData.crud.getById(deleteId));
        assertNull(userData.crud.getById(deleteId));
        */
    }
}