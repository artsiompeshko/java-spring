package com.apeshko.javaspring.service.impl;

import com.apeshko.javaspring.dao.EventDao;
import com.apeshko.javaspring.dao.UserDao;
import com.apeshko.javaspring.model.Event;
import com.apeshko.javaspring.model.User;
import com.apeshko.javaspring.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class EventServiceImpl implements EventService {
    @Autowired
    private EventDao<Event> eventDao;

    @Override
    public Event getById(long eventId) {
        return eventDao.getById(eventId);
    }

    @Override
    public List<Event> getByTitle(String title, int pageSize, int pageNum) {
        return eventDao.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getForDay(Date day, int pageSize, int pageNum) {
        return eventDao.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event create(Event event) {
        return eventDao.save(event);
    }

    @Override
    public Event update(Event event) {
        return eventDao.update(event);
    }

    @Override
    public boolean delete(long eventId) {
        return eventDao.delete(eventId);
    }
}
