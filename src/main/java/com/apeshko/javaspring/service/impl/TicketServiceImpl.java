package com.apeshko.javaspring.service.impl;

import com.apeshko.javaspring.dao.TicketDao;
import com.apeshko.javaspring.model.Event;
import com.apeshko.javaspring.model.Ticket;
import com.apeshko.javaspring.model.User;
import com.apeshko.javaspring.model.impl.TicketModel;
import com.apeshko.javaspring.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private TicketDao<Ticket> ticketDao;

    @Override
    public Ticket book(long userId, long eventId, int place, Ticket.Category category) {
        List<Ticket> tickets = ticketDao.getBookedTicketsByEventId(eventId, Integer.MAX_VALUE, 1);

        if (tickets.stream().anyMatch(ticket -> ticket.getPlace() == place)) {
            logger.error("Place {} was already booked for event {}", place, eventId);

            throw new IllegalStateException("This place is already booked");
        }

        Ticket ticket = new TicketModel(eventId, userId, category, place);

        return ticketDao.save(ticket);
    }

    @Override
    public List<Ticket> getBooked(User user, int pageSize, int pageNum) {
        return ticketDao.getBookedTicketsByUserId(user.getId(), pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBooked(Event event, int pageSize, int pageNum) {
        return ticketDao.getBookedTicketsByEventId(event.getId(), pageSize, pageNum);
    }

    @Override
    public boolean cancel(long ticketId) {
        return ticketDao.delete(ticketId);
    }
}
