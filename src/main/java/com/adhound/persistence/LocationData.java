package com.adhound.persistence;

import com.adhound.entity.Location;
import com.adhound.entity.User;
import com.adhound.service.CrudService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Location data.
 */
public class LocationData {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The CRUD.
     */
    public CrudService crud;

    /**
     * Instantiates a new CRUD service to access the CRUD methods with this class.
     */
    public LocationData() {
        this.crud = new CrudService(Location.class);
    }

}
