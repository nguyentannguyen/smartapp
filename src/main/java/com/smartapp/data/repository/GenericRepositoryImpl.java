package com.smartapp.data.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class GenericRepositoryImpl <T, PK extends Serializable> implements GenericRepository<T, PK> {

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericRepositoryImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public T create(T t) {
        this.entityManager.persist(t);
        return t;
    }

    @Override
    public T read(PK id) {
        return this.entityManager.find(entityClass, id);
    }

    @Override
    public T update(T t) {
        return this.entityManager.merge(t);
    }

    @Override
    public void delete(T t) {
        t = this.entityManager.merge(t);
        this.entityManager.remove(t);
    }
}
