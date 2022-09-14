package com.barzykin.personal.app.repositories.helpers;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

public class EntityManagerHelper {
    private final SessionFactory sessionFactory;


    private EntityManagerHelper() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    private static class EntityManagerHelperHolder {
        private static final EntityManagerHelper INSTANCE = new EntityManagerHelper();
    }

    public static EntityManagerHelper getInstance() {
        return EntityManagerHelperHolder.INSTANCE;
    }


    public EntityManager getEntityManager() {
        return sessionFactory.createEntityManager();
    }


}
