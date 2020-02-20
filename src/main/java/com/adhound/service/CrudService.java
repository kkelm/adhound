package com.adhound.service;

import com.adhound.persistence.SessionFactoryProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * The CRUD service.
 *
 * @param <GenericType> represents an unknown return type
 */
public class CrudService<GenericType> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Declare a generic class and type that is then set in the constructor.
     */
    private  Class<GenericType> entityClass;

    /**
     * The initialize a SessionFactory.
     */
    private SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Injects a new class when this service is instantiated.
     * @param entity the entity
     */
    public CrudService(Class<GenericType> entity) {
        this.entityClass = entity;
    }

    /**
     * Gets all records related to a certain generic type.
     * @return the all users
     */
    public List<GenericType> getAll() {
        logger.info("getAll Started");
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<GenericType> query = criteriaBuilder.createQuery(entityClass);
        Root<GenericType> root = query.from(entityClass);
        List<GenericType> list = session.createQuery(query).getResultList();
        session.close();
        logger.info("getAll Finished");
        return list;
    }

    /**
     * Gets record data related to a certain generic type.
     * @param id the id
     * @return the record data
     */
    public GenericType getById(int id) {
        logger.info(id + " ID was passed to getById");
        logger.info("getById Started");
        Session session = sessionFactory.openSession();
        GenericType user = session.get(entityClass, id);
        session.close();
        logger.info("getById Finished");
        return user;
    }

    /**
     * Updates a user record in the database.
     * @param type Entity to be updated
     */
    public void updateRecords(GenericType type) {
        logger.info("updateRecords Started");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(type);
        transaction.commit();
        session.close();
        logger.info("updateRecords Finished");
    }

    /**
     * Inserts a new record for the entity into the database.
     * @param type Entity to be inserted
     * @return id of of the new record
     */
    public Serializable insertRecord(GenericType type) {
        logger.info("insertRecord Started");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable id = session.save(type);
        transaction.commit();
        session.close();
        logger.info("insertRecord Finished");
        return id;
    }

    /**
     * Deletes an entity record from the database.
     * @param type Entity to be deleted
     */
    public void deleteRecord(GenericType type) {
        logger.info("deleteRecord Started");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(type);
        transaction.commit();
        session.close();
        logger.info("deleteRecord Finished");
    }
}
