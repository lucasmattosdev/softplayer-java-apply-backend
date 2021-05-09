package br.com.lucasmattos.softplayer.java.apply.backend.hibernate;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class HibernateConnection {
    @PersistenceContext(unitName = "SoftplayerJavaApplyPU")
    private EntityManager entityManager;

    public Session getSession() {
        return (Session) entityManager.getDelegate();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
