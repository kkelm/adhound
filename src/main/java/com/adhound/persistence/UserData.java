package com.adhound.persistence;

import com.adhound.entity.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * This is the User DAO
 */
public class UserData {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<User> getAllUsersData() {
        Session session = sessionFactory.openSession();
        // Create a CriteriaBuilder instance by calling the getCriteriaBuilder method on the Session
        // instance to build a query statement.
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

    public User getUserData(int id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
/*
        // Create a CriteriaBuilder instance by calling the getCriteriaBuilder method on the Session
        // instance to build a query statement.
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        // Create a query for the User class and sets the criteria to a type of User.
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        // Creates the FROM clause for the query and sets it to a type of User.
        Root<User> root = query.from(User.class);
        // Select the id variable from the User class.
        Expression<Integer> databaseColumn = root.get("id");
        // Create the WHERE clause and use the LIKE option in the clause.
        //query.where(criteriaBuilder.like(databaseColumn, "%" + id + "%"));
        query.where(criteriaBuilder.equal(databaseColumn, id));
        // Run the query and set the results to a list of user.
        List<User> user = session.createQuery(query).getResultList();
*/
        session.close();
        return user;
    }

}
