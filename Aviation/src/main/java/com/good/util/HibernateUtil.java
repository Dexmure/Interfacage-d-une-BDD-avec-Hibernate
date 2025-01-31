// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
package com.good.util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

    static {
        try {
            // Créer une SessionFactory à partir de la configuration Hibernate (hibernate.cfg.xml)
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log l'exception si la SessionFactory échoue
            System.err.println("La création de la SessionFactory a échoué : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Fermer la SessionFactory
        getSessionFactory().close();
    }

}
