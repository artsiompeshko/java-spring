package com.apeshko.javaspring.dao;

import java.util.Date;
import java.util.List;

public interface EventDao<T> extends Dao<T> {
    List<T> getEventsByTitle(String title, int pageSize, int pageNum);
    List<T> getEventsForDay(Date day, int pageSize, int pageNum);
}
