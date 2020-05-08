package com.adhound.service;

import com.adhound.entity.User;
import com.adhound.persistence.SessionFactoryProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class Authentication {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public Authentication() {}

    /**
     * This method gets the user ID of the current user.
     * @param username
     * @return
     */
    public int userAuthentication(String username) {

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

            Root<User> root = query.from(User.class);

            Expression<String> usernameColumn = root.get("username");

            query.where(criteriaBuilder.equal(usernameColumn, username));

            List<User> user = session.createQuery(query).getResultList();
            session.close();

            if (user.size() > 0) {
                return user.iterator().next().getId();
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

        return 0;
    }
}
