package com.apeshko.javaspring.service;

import com.apeshko.javaspring.model.Event;

import java.util.Date;
import java.util.List;

public interface EventService {
    Event getById(long eventId);
    List<Event> getByTitle(String title, int pageSize, int pageNum);
    List<Event> getForDay(Date day, int pageSize, int pageNum);
    Event create(Event event);
    Event update(Event event);
    boolean delete(long eventId);
}
