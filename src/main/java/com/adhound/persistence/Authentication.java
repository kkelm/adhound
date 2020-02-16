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

public class Authentication {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public boolean userLogin(String username, String password) {

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

            Root<User> root = query.from(User.class);

            Expression<String> usernameColumn = root.get("username");
            Expression<String> passwordColumn = root.get("password");

            query.where(criteriaBuilder.equal(usernameColumn, username), criteriaBuilder.equal(passwordColumn, password));

            List<User> user = session.createQuery(query).getResultList();
            session.close();

            if (user.size() > 0) {
                return true;
            }
        }
        catch (Exception e) {
            logger.error(e);
        }

        return false;
    }
}
