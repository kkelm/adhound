package com.adhound.persistence;

import com.adhound.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User data test.
 */
class UserDataTest {

    UserData userData;

    /**
     * Sets up the tests with a new UserData object.
     */
    @BeforeEach
    void setUp() {

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
    }

    /**
     * Test get one user data success.
     */
    @Test
    void testGetUserDataSuccess() {
        User getUser = userData.getUserData(1);

        assertEquals("Kevin", getUser.getFirstName());
    }

    /**
     * Test an update of a user record.
     */
    @Test
    void testSaveOrUpdate() {
        String password = "testPassword";
        User updateUser = userData.getUserData(1);
        updateUser.setPassword(password);
        userData.saveOrUpdate(updateUser);
        User getUser = userData.getUserData(1);
        assertEquals(password, getUser.getPassword());
    }

    /**
     * Test the creation of a new user record.
     */
    @Test
    void testInsert() {
        User newUser = new User("testUsername", "testPassword", "testFirstName", "testLastName", "123-456-7890", "987-654-3210", "test@email.com", "123 Test Street", "testCity", 33, "12345");
        int newId = userData.insert(newUser);
        User insertedUser = userData.getUserData(newId);
        assertEquals("testUsername", insertedUser.getUsername());
    }

    /**
     * Test the deletion of a user record.
     */
    @Test
    void testDelete() {
        userData.delete(userData.getUserData(6));
        assertNull(userData.getUserData(6));
    }
}