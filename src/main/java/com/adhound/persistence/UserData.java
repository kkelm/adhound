package com.adhound.persistence;

import com.adhound.entity.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * This is the User DAO
 */
public class UserData {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<User> getUserData(String username, String password) {
        Session session = sessionFactory.openSession();
        // Creates the WHERE clause for the query.
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        // Create a query for the User class and sets the criteria to a type of User.
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        // Creates the FROM clause for the query and sets it to a type of User.
        Root<User> root = query.from(User.class);
        // Run the query and set the results to a list of user.
        List<User> user = session.createQuery(query).getResultList();

        session.close();
        return user;
    }

}
