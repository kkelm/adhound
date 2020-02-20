package com.adhound.persistence;

import com.adhound.entity.User;
import com.adhound.entity.UserRole;

import com.adhound.service.CrudService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

/**
 * This is the User DAO
 */
public class UserData {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public CrudService crud;

    /**
     * Instantiates a new CRUD service to access the CRUD methods with this class.
     */
    public UserData() {
        this.crud = new CrudService(User.class);
    }
/*
    public User register () {
        User newUser = new User(newUsername, "testPassword", "testFirstName", "testLastName", "123-456-7890", "987-654-3210", "test@email.com", "123 Test Street", "testCity", 33, "12345");
        int newId = (int) this.crud.insertRecord(newUser);

        return newUser;
    }
*/
}
