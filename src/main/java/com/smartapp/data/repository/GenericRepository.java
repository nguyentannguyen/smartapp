package com.smartapp.data.repository;

import java.io.Serializable;

public interface GenericRepository<T, PK extends Serializable> {
    T create(T t);
    T read(PK id);
    T update(T t);
    void delete(T t);
}