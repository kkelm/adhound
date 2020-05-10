package com.adhound.service;

import com.adhound.persistence.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTest {

    UserData userData;

    @BeforeEach
    void setUp() {
        userData = new UserData();
    }

    @Test
    void testUserAuthentication() {

        assertEquals(2, userData.userAuthentication("distributor@email.com"));

    }
}