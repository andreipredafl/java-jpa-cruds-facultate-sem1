package com.example.it_company.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public abstract class AbstractRepository<T> {
    private static EntityManager em = Persistence.createEntityManagerFactory("SGSMPersistenceUnit").createEntityManager();

    public static EntityManager getEm() {
        return em;
    }

    public T create(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    public T update(T entity) {
        em.getTransaction().begin();
        T managedEntity = em.merge(entity);
        em.getTransaction().commit();
        return managedEntity;
    }
}
