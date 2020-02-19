package com.adhound.persistence;

import com.adhound.entity.User;
import com.adhound.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User data test.
 */
class UserDataTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    UserData userData;

    /**
     * Sets up the tests with a new UserData object.
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
        List<User> users = userData.getAllUsers();
        int currentSize = users.size();
        assertEquals(currentSize, users.size());
        logger.info("Got all users");
    }

    /**
     * Test get one user data success.
     */
    @Test
    void testGetByIdUser() {
        User getUser = userData.getByIdUser(1);

        assertEquals("Kevin", getUser.getFirstName());

        logger.info("Got user information for ID: " + getUser.getId());
    }


    /**
     * Test the creation of a new user record.
     */
    @Test
    void testInsert() {
        String newUsername = "testUsername" + Math.round(Math.random()*100);
        User newUser = new User(newUsername, "testPassword", "testFirstName", "testLastName", "123-456-7890", "987-654-3210", "test@email.com", "123 Test Street", "testCity", 33, "12345");
        int newId = userData.insert(newUser);
        User insertedUser = userData.getByIdUser(newId);
        assertEquals(newUsername, insertedUser.getUsername());

        logger.info("Inserted record for ID: " + newId);
    }

    /**
     * Test an update of a user record.
     */
    @Test
    void testSaveOrUpdate() {
        String password = "testPassword";
        User updateUser = userData.getByIdUser(1);
        updateUser.setPassword(password);
        userData.saveOrUpdate(updateUser);
        User getUser = userData.getByIdUser(1);
        assertEquals(password, getUser.getPassword());

        logger.info("Updated the password for ID: " + updateUser.getId());
    }

    /**
     * Test the deletion of a user record.
     */
    @Test
    void testDelete() {
        List<User> users = userData.getAllUsers();

        int deleteId = users.get((users.size() - 1)).getId();

        userData.delete(userData.getByIdUser(deleteId));
        assertNull(userData.getByIdUser(deleteId));

        logger.info("Deleted record for ID: " + deleteId);
    }
}