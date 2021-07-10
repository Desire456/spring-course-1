package org.example.java;

import org.example.PostgresConnector;

import javax.sql.DataSource;

public class JavaPostgresConnector extends PostgresConnector {
    public JavaPostgresConnector(DataSource dataSource) {
        super(dataSource);
    }
}
