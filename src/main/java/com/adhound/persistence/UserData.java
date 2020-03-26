package com.adhound.persistence;

import com.adhound.entity.User;

import com.adhound.service.Authentication;
import com.adhound.service.CrudService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is the User DAO
 */
public class UserData {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public CrudService crud;

    public Authentication authentication;

    /**
     * Instantiates a new CRUD service to access the CRUD methods with this class.
     */
    public UserData() {
        this.crud = new CrudService(User.class);
        this.authentication = new Authentication();
    }

}
