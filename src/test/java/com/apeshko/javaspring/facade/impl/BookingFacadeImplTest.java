package com.apeshko.javaspring.facade.impl;

import com.apeshko.javaspring.facade.BookingFacade;
import com.apeshko.javaspring.model.Event;
import com.apeshko.javaspring.model.impl.EventModel;
import com.apeshko.javaspring.service.EventService;
import com.apeshko.javaspring.service.TicketService;
import com.apeshko.javaspring.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("unit")
@ExtendWith(SpringExtension.class)
class BookingFacadeImplTest {
    @MockBean
    private UserService userService;

    @MockBean
    private TicketService ticketService;

    @MockBean
    private EventService eventService;

    private BookingFacade bookingFacade;

    @BeforeEach
    void setUp() {
        bookingFacade = new BookingFacadeImpl(userService, ticketService, eventService);
    }

    @Test
    void getEventById_ShouldReturnEventById() {
        // given
        Event expectedEvent = new EventModel(1L, "Lyon vs PSG", new Date());
        when(eventService.getById(1)).thenReturn(expectedEvent);

        // when
        Event actualResult = bookingFacade.getEventById(1L);

        // then
        assertEquals(expectedEvent, actualResult);
    }
}