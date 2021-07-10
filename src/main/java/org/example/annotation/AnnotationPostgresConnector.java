package org.example.annotation;

import org.example.PostgresConnector;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class AnnotationPostgresConnector extends PostgresConnector {
    public AnnotationPostgresConnector(DataSource dataSource) {
        super(dataSource);
    }
}
