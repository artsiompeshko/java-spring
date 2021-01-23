package com.apeshko.javaspring.config;

import com.apeshko.javaspring.storage.Storage;
import com.apeshko.javaspring.storage.impl.InMemoryStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.apeshko.javaspring.service")
public class ApplicationConfig {
    @Bean
    public Storage storage() {
        return new InMemoryStorage();
    }
}
