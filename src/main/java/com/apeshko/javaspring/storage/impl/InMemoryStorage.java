package com.apeshko.javaspring.storage.impl;

import com.apeshko.javaspring.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:application.properties")
public class InMemoryStorage implements Storage {
    @Value("${storage.data.path}")
    String initialDataPath;

    final Map<String, Object> data = new HashMap<>();

    @Override
    public Object getByKey(String key) {
        return data.get(key);
    }

    @Override
    public List<Object> getAllByKeyPrefix(String keyPrefix) {
        return data.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(keyPrefix))
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public Object save(String key, Object value) {
        data.put(key, value);

        return value;
    }

    @Override
    public Object remove(String key) {
        return data.remove(key);
    }

    @PostConstruct
    public void init() throws IOException {
        // TODO: initial data
    }
}
