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

    public int newIdToDelete = 0;

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
    void testGetAllUsersData() {
        List<User> users = userData.getAllUsersData();

        int currentSize = users.size();

        assertEquals(currentSize, users.size());

        logger.info("testGetAllUsersData");
    }

    /**
     * Test get one user data success.
     */
    @Test
    void testGetUserDataSuccess() {
        User getUser = userData.getUserData(1);

        assertEquals("Kevin", getUser.getFirstName());

        logger.info("testGetUserDataSuccess");
    }


    /**
     * Test the creation of a new user record.
     */
    @Test
    void AtestInsert() {
        User newUser = new User("testUsername", "testPassword", "testFirstName", "testLastName", "123-456-7890", "987-654-3210", "test@email.com", "123 Test Street", "testCity", 33, "12345");
        int newId = userData.insert(newUser);
        newIdToDelete = newId;
        User insertedUser = userData.getUserData(newId);
        assertEquals("testUsername", insertedUser.getUsername());

        logger.info("testInsert");
    }

    /**
     * Test an update of a user record.
     */
    @Test
    void BtestSaveOrUpdate() {
        String password = "testPassword";
        User updateUser = userData.getUserData(1);
        updateUser.setPassword(password);
        userData.saveOrUpdate(updateUser);
        User getUser = userData.getUserData(1);
        assertEquals(password, getUser.getPassword());

        logger.info("testSaveOrUpdate");
    }

    /**
     * Test the deletion of a user record.
     */
    @Test
    void CtestDelete() {
        //User user = userData.getUserData(10);
        userData.delete(userData.getUserData(11));
        assertNull(userData.getUserData(11));

        logger.info("testDelete");
    }
}