package com.apeshko.javaspring.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    T getById(long id);
    List<T> getAll();
    T save (T t);
    T update(T t);
    boolean delete(long id);
}
