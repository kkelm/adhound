package com.adhound.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTest {

    Authentication authentication;

    @BeforeEach
    void setUp() {
        authentication = new Authentication();
    }

    @Test
    void userLogin() {

        assertEquals(true, authentication.userLogin("kkelm", "$2y$10$J5Dy.vfoLfhE6HKq3Mal3eIdn9kdVPO7NahHpNWLgYiMoyqwn17QW"));

    }
}