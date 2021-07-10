package org.example.annotation;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ExtPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {
    public ExtPropertySourcesPlaceholderConfigurer() {
        setLocations(new ClassPathResource("db.properties"));
    }
}
