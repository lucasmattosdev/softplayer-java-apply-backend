package br.com.lucasmattos.softplayer.java.apply.backend.service;

import br.com.lucasmattos.softplayer.java.apply.backend.entity.AbstractEntity;
import br.com.lucasmattos.softplayer.java.apply.backend.hibernate.HibernateConnection;
import org.hibernate.Session;

import javax.inject.Inject;

public abstract class AbstractService<T extends AbstractEntity> {
    @Inject
    HibernateConnection hibernateConnection;

    public Session getSession(){
        return hibernateConnection.getSession();
    }

    protected abstract Class<T> getServiceClass();

    public T getById(final Long id) {
        return getSession().get(getServiceClass(), id);
    }

    public T save(T entity) {
        if (entity.getId() != null) {
            return hibernateConnection.getEntityManager().merge(entity);
        } else {
            hibernateConnection.getEntityManager().persist(entity);
        }
        return entity;
    }

    public void delete(T entity) {
        if (entity.getId() != null) {
            hibernateConnection.getEntityManager().remove(entity);
        }
    }

}
