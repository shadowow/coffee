package com.coffee.dao;


import com.coffee.logic.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Юленька on 24.03.2016.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .setProperty("hibernate.show_sql", "true")
                    .addAnnotatedClass(Status.class)
                    .addAnnotatedClass(Order.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(BasketEntry.class)
                    .addAnnotatedClass(Bakery.class)
                    .addAnnotatedClass(Desert.class)
                    .addAnnotatedClass(Drink.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
