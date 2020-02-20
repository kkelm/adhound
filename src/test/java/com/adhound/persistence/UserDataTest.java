package com.adhound.persistence;

import com.adhound.entity.Location;
import com.adhound.entity.User;
import com.adhound.entity.UserRole;
import com.adhound.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User data test.
 */
class UserDataTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    UserData userData;
    LocationData locationData;

    /**
     * Sets up the tests with a new UserData object.
     */
    @BeforeEach
    void setUp() {

        //Database database = Database.getInstance();
        //database.createDatabase("adhound.sql");

        userData = new UserData();
        locationData = new LocationData();

    }

    /**
     * Test get all users data.
     */
    @Test
    void testGetAllUsers() {
        List<User> users = userData.crud.getAll();
        int currentSize = users.size();
        assertEquals(currentSize, users.size());
        logger.info("Got all users");
    }

    /**
     * Test get one user data success.
     */
    @Test
    void testGetByIdUser() {
        User getUser = (User) userData.crud.getById(1);

        assertEquals("Kevin", getUser.getFirstName());

        logger.info("Got user information for ID: " + getUser.getId());
    }


    /**
     * Test the creation of a new user record.
     */
    @Test
    void testInsertUser() {
        String newUsername = "testUsername" + Math.round(Math.random()*100);
        User newUser = new User(newUsername, "testPassword", "testFirstName", "testLastName", "123-456-7890", "987-654-3210", "test@email.com", "123 Test Street", "testCity", 33, "12345");
        int newId = (int) userData.crud.insertRecord(newUser);

        UserRole userRole = new UserRole(newUser.getUsername());
        Serializable userRoleId = userData.crud.insertRecord(userRole);

        User insertedUser = (User) userData.crud.getById(newId);
        assertEquals(newUsername, insertedUser.getUsername());

        logger.info("Inserted record for ID: " + newId);
    }

    /**
     * Test an update of a user record.
     */
    @Test
    void testUpdateUser() {
        String password = "testPassword";
        User updateUser = (User) userData.crud.getById(1);
        updateUser.setPassword(password);
        userData.crud.updateRecords(updateUser);
        User getUser = (User) userData.crud.getById(1);
        assertEquals(password, getUser.getPassword());

        logger.info("Updated the password for ID: " + updateUser.getId());
    }

    /**
     * Test the deletion of a user record.
     */
    @Test
    void testDeleteUser() {
        List<User> users = userData.crud.getAll();

        int deleteId = users.get((users.size() - 1)).getId();

        userData.crud.deleteRecord(userData.crud.getById(deleteId));
        assertNull(userData.crud.getById(deleteId));

        logger.info("Deleted record for ID: " + deleteId);
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

        assertEquals(newLocationName, getRecord.getName());
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
        Set <Location> userLocations = user.getLocations();
        // Get a location from the set
        Location userLocation = userLocations.iterator().next();
        // Get the location name, add "Updated" to the name, and set the new name
        userLocation.setName(userLocation.getName() + " Updated");
        // Update the location database record with the updated updated object
        locationData.crud.updateRecords(userLocation);
        // Get the updated location from the database based on the user location ID
        Location updatedRecord = (Location) locationData.crud.getById(userLocation.getId());

        logger.info("End Location Update");

        assertEquals(userLocation.getName(), updatedRecord.getName());
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
        // Remove location from user object
        userLocations.remove(userLocation);
        // Remove location from the database
        locationData.crud.deleteRecord(userLocation);

        logger.info("Deleted record for ID: " + deleteId);
        /*
        int deleteId = users.get((users.size() - 1)).getId();
        userData.crud.deleteRecord(userData.crud.getById(deleteId));
        assertNull(userData.crud.getById(deleteId));
        */
    }

}