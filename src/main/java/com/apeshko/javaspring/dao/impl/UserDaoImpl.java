package com.apeshko.javaspring.dao.impl;

import com.apeshko.javaspring.dao.UserDao;
import com.apeshko.javaspring.model.User;
import com.apeshko.javaspring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class UserDaoImpl implements UserDao<User> {
    private static final String STORAGE_PREFIX = "user";

    private Storage storage;

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Storage getStorage() {
        return storage;
    }

    private String getStorageKey(long id) {
        return STORAGE_PREFIX + id;
    }

    @Override
    public User getByEmail(String email) {
        return this.getAll().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        // TODO: create partitioning
        return this.getAll().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public User getById(long id) {
        return (User) storage.getByKey(getStorageKey(id));
    }

    @Override
    public List<User> getAll() {
        return storage.getAllByKeyPrefix(STORAGE_PREFIX).stream()
                .map(object -> (User) object)
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        user.setId(new Random().nextLong());

        return (User) storage.save(getStorageKey(user.getId()), user);
    }

    @Override
    public User update(User user) {
        return (User) storage.save(getStorageKey(user.getId()), user);
    }

    @Override
    public boolean delete(long id) {
        return storage.remove(getStorageKey(id)) != null;
    }
}
