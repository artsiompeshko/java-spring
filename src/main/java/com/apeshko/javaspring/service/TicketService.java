package com.apeshko.javaspring.service;

import com.apeshko.javaspring.model.Event;
import com.apeshko.javaspring.model.Ticket;
import com.apeshko.javaspring.model.User;

import java.util.List;

public interface TicketService {
    Ticket book(long userId, long eventId, int place, Ticket.Category category);
    List<Ticket> getBooked(User user, int pageSize, int pageNum);
    List<Ticket> getBooked(Event event, int pageSize, int pageNum);
    boolean cancel(long ticketId);
}
