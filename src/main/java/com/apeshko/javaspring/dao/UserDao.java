package com.apeshko.javaspring.dao;

import java.util.List;

public interface UserDao<T> extends Dao<T> {
    T getByEmail(String email);
    List<T> getUsersByName(String name, int pageSize, int pageNum);
}
