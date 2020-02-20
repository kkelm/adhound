package com.adhound.persistence;

import com.adhound.entity.Location;
import com.adhound.entity.User;
import com.adhound.service.CrudService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import java.util.List;

public class LocationData {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public CrudService crud;

    /**
     * Instantiates a new CRUD service to access the CRUD methods with this class.
     */
    public LocationData() {
        this.crud = new CrudService(Location.class);
    }

}
