package com.apeshko.javaspring.dao.impl;

import com.apeshko.javaspring.dao.EventDao;
import com.apeshko.javaspring.model.Event;
import com.apeshko.javaspring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class EventDaoImpl implements EventDao<Event> {
    private static final String STORAGE_PREFIX = "event";
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
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        // TODO: create partitioning
        return this.getAll().stream()
                .filter(event -> event.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        // TODO: create partitioning
        return this.getAll().stream()
                .filter(event -> event.getDate().equals(day))
                .collect(Collectors.toList());
    }

    @Override
    public Event getById(long id) {
        return (Event) storage.getByKey(getStorageKey(id));
    }

    @Override
    public List<Event> getAll() {
        return storage.getAllByKeyPrefix(STORAGE_PREFIX).stream()
            .map(object -> (Event) object)
            .collect(Collectors.toList());
    }

    @Override
    public Event save(Event event) {
        event.setId(new Random().nextLong());

        return (Event) storage.save(getStorageKey(event.getId()), event);
    }

    @Override
    public Event update(Event event) {
        return (Event) storage.save(getStorageKey(event.getId()), event);
    }

    @Override
    public boolean delete(long id) {
        return storage.remove(getStorageKey(id)) != null;
    }
}
