package com.apeshko.javaspring.model.impl;

import com.apeshko.javaspring.model.Event;

import java.util.Date;

public class EventModel implements Event {
    private long id;
    private String title;
    private Date date;

    public EventModel(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public EventModel(long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
