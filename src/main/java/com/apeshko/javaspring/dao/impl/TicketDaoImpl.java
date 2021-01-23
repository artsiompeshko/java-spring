package com.apeshko.javaspring.dao.impl;

import com.apeshko.javaspring.dao.TicketDao;
import com.apeshko.javaspring.model.Ticket;
import com.apeshko.javaspring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class TicketDaoImpl implements TicketDao<Ticket> {
    private static final String STORAGE_PREFIX = "ticket";
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
    public List<Ticket> getBookedTicketsByUserId(long userId, int pageSize, int pageNum) {
        // TODO: create partitioning
        return this.getAll().stream()
                .filter(ticket -> ticket.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTicketsByEventId(long eventId, int pageSize, int pageNum) {
        // TODO: create partitioning
        return this.getAll().stream()
                .filter(ticket -> ticket.getEventId() == eventId)
                .collect(Collectors.toList());
    }

    @Override
    public Ticket getById(long id) {
        return (Ticket) storage.getByKey(getStorageKey(id));
    }

    @Override
    public List<Ticket> getAll() {
        return storage.getAllByKeyPrefix(STORAGE_PREFIX).stream()
            .map(object -> (Ticket) object)
            .collect(Collectors.toList());
    }

    @Override
    public Ticket save(Ticket ticket) {
        ticket.setId(new Random().nextLong());

        return (Ticket) storage.save(getStorageKey(ticket.getId()), ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return (Ticket) storage.save(getStorageKey(ticket.getId()), ticket);
    }

    @Override
    public boolean delete(long id) {
        return storage.remove(getStorageKey(id)) != null;
    }
}
