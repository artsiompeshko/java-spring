package com.apeshko.javaspring.storage;

import java.util.List;

public interface Storage {
    Object getByKey(String key);
    List<Object> getAllByKeyPrefix(String keyPrefix);
    Object save(String key, Object value);
    Object remove(String key);
}
