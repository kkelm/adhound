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
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        userData = new UserData();
    }

    /**
     * Gets all users data.
     */
    @Test
    void getAllUsersData() {
        List<User> users = userData.getAllUsersData();
        assertEquals(5, users.size());
    }

    /**
     * Gets user data success.
     */
    @Test
    void getUserDataSuccess() {
        User getUser = userData.getUserData(1);

        assertEquals("Kevin", getUser.getFirstName());
    }
}