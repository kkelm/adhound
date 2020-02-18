package com.adhound.persistence;

import com.adhound.entity.User;
import com.adhound.entity.UserRole;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * This is the User DAO
 */
public class UserData {

    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all users data.
     *
     * @return the all users data
     */
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

    /**
     * Gets user data for one user.
     *
     * @param id the id
     * @return the user data
     */
    public User getUserData(int id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        //UserRole userRole = session.get(UserRole.class, user.getUsername());
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

    /**
     * Updates a user record in the database.
     *
     * @param user User to be updated
     */
    public void saveOrUpdate(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }

    /**
     * Inserts a new user record in the database.
     *
     * @param user User to be inserted
     * @return id of of the new record
     */
    public int insert(User user) {
        int userId = 0;
        String userRoleId;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        userId = (int)session.save(user);

        UserRole userRole = new UserRole(user.getUsername());
        userRoleId = (String)session.save(userRole);

        transaction.commit();
        session.close();
        return userId;
    }

    /**
     * Delete a user record from the database.
     *
     * @param user User to be deleted
     */
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

}
