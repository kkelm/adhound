package com.adhound.persistence;

import com.adhound.entity.User;
import com.adhound.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User data test.
 */
class UserDataTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    UserData userData;

    /**
     * Sets up the tests with a new objects.
     */
    @BeforeEach
    void setUp() {
        //Database database = Database.getInstance();
        //database.createDatabase("adhound.sql");
        userData = new UserData();
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

        newUser = (User) userData.crud.getById(newId);

        UserRole userRole = new UserRole(newUser, newUser.getUsername());
        Serializable userRoleId = userData.crud.insertRecord(userRole);

        User insertedUser = (User) userData.crud.getById(newId);
        assertEquals(newUser, insertedUser);

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
        assertEquals(getUser, updateUser);
        logger.info("Updated the password for ID: " + updateUser.getId());
    }

    /**
     * Test the deletion of a user record.
     */
    @Test
    void testDeleteUser() {
        List<User> allUsers = userData.crud.getAll();
        int deleteId = allUsers.get((allUsers.size() - 1)).getId();
        userData.crud.deleteRecord(userData.crud.getById(deleteId));
        assertNull(userData.crud.getById(deleteId));
        // TODO: Could compare objects but couldn't there be a performance issue if the table was large?
        // List<User> allUsersMinusOne = userData.crud.getAll();
        // assertNotEquals(allUsersMinusOne, allUsers);
        logger.info("Deleted record for ID: " + deleteId);
    }

}