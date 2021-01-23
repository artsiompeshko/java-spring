package com.apeshko.javaspring.dao;

import com.apeshko.javaspring.model.User;

import java.util.List;

public interface TicketDao<T> extends Dao<T> {
    List<T> getBookedTicketsByUserId(long userId, int pageSize, int pageNum);
    List<T> getBookedTicketsByEventId(long eventId, int pageSize, int pageNum);
}
