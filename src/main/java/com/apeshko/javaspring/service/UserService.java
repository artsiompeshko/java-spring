package com.apeshko.javaspring.service;

import com.apeshko.javaspring.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User getById(long id);
    User create(User user);
    User getByEmail(String email);
    List<User> getByName(String name, int pageSize, int pageNum);
    User update(User user);
    boolean delete(long userId);
}
