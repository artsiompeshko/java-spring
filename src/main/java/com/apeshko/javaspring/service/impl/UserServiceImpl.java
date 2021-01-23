package com.apeshko.javaspring.service.impl;

import com.apeshko.javaspring.dao.UserDao;
import com.apeshko.javaspring.model.User;
import com.apeshko.javaspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao<User> userDao;

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User create(User user) {
        return userDao.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public List<User> getByName(String name, int pageSize, int pageNum) {
        return userDao.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(long userId) {
        return userDao.delete(userId);
    }
}
