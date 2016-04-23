package com.coffee.dao;

/**
 * Created by Юленька on 28.03.2016.
 */

import org.hibernate.Session;

public class DAO {

    private Session session;

    public Session openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }

    public void closeSession() {
        if (session.isOpen()) {
            session.close();
        }
    }
}
