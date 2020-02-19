package com.adhound.service;

import com.adhound.entity.*;
import com.adhound.persistence.SessionFactoryProvider;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * The type Crud service.
 *
 * @param <GenericType> represents an unknown return type
 */
public class CrudService<GenericType> {

    /**
     * The entity/class passed into the constructor.
     */
    Class entityClass;

    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Instantiates a new CRUD service and sets entityClass to the passed entity/class.
     *
     * @param entity the entity
     */
    public CrudService(Class entity) {
        entityClass = entity;
    }

    /**
     * Gets all records related to a certain generic type.
     *
     * @return the all users
     */
    public List<GenericType> getAll() {

        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<GenericType> query = criteriaBuilder.createQuery(entityClass);
        Root<GenericType> root = query.from(entityClass);

        List<GenericType> type = session.createQuery(query).getResultList();

        session.close();

        return type;

    }

    /**
     * Gets record data related to a certain generic type.
     *
     * @param id the id
     * @return the record data
     */
    public GenericType getById(int id) {
        Session session = sessionFactory.openSession();
        GenericType type = (GenericType) session.get(entityClass, id);
        session.close();
        return type;
    }

    /**
     * Updates a user record in the database.
     *
     * @param type Entity to be updated
     */
    public void updateRecords(GenericType type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(type);
        transaction.commit();
        session.close();
    }

    /**
     * Inserts a new record for the entity into the database.
     *
     * @param type Entity to be inserted
     * @return id of of the new record
     */
    public Serializable insertRecord(GenericType type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable id = session.save(type);

        //UserRole userRole = new UserRole(user.getUsername());
        //userRoleId = (String)session.save(userRole);

        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Deletes an entity record from the database.
     *
     * @param type Entity to be deleted
     */
    public void deleteRecord(GenericType type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(type);
        transaction.commit();
        session.close();
    }
}
