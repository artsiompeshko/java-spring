package com.apeshko.javaspring.facade.impl;

import com.apeshko.javaspring.facade.BookingFacade;
import com.apeshko.javaspring.model.Event;
import com.apeshko.javaspring.model.Ticket;
import com.apeshko.javaspring.model.User;
import com.apeshko.javaspring.model.impl.EventModel;
import com.apeshko.javaspring.model.impl.UserModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag("integration")
class BookingFacadeImplIntegrationTest {
    @Autowired
    private BookingFacade bookingFacade;

    @Test
    public void ticketBooking_ShouldBookTicket() {
        User mockUser = bookingFacade.createUser(new UserModel("Artsiom", "peshkoartembsu@gmail.com"));
        Event mockEvent = bookingFacade.createEvent(new EventModel("Lyon vs PSG", new Date()));

        Ticket ticket = bookingFacade.bookTicket(mockUser.getId(), mockEvent.getId(), 1, Ticket.Category.PREMIUM);

        assertEquals(bookingFacade.getBookedTickets(mockUser, 1, 1), Arrays.asList(ticket));
        assertEquals(mockEvent, bookingFacade.getEventById(mockEvent.getId()));
        assertEquals(mockUser, bookingFacade.getUserById(mockUser.getId()));
    }

    @Test
    public void ticketBooking_shouldThrowExceptionIfPlaceIsAlreadyBooked() {
        User mockUser = bookingFacade.createUser(new UserModel("Artsiom", "peshkoartembsu@gmail.com"));
        Event mockEvent = bookingFacade.createEvent(new EventModel("Lyon vs PSG", new Date()));

        Ticket ticket = bookingFacade.bookTicket(mockUser.getId(), mockEvent.getId(), 1, Ticket.Category.PREMIUM);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            bookingFacade.bookTicket(mockUser.getId(), mockEvent.getId(), 1, Ticket.Category.PREMIUM);
        });

        assertEquals("This place is already booked", exception.getMessage());
    }

    @Test
    public void ticketBooking_ShouldBookTicketAndCancelIt() {
        User mockUser = bookingFacade.createUser(new UserModel("Artsiom", "peshkoartembsu@gmail.com"));
        Event mockEvent = bookingFacade.createEvent(new EventModel("Lyon vs PSG", new Date()));

        Ticket ticket = bookingFacade.bookTicket(mockUser.getId(), mockEvent.getId(), 1, Ticket.Category.PREMIUM);
        bookingFacade.cancelTicket(ticket.getId());

        assertEquals(0, bookingFacade.getBookedTickets(mockUser, 1, 1).size());
    }
}