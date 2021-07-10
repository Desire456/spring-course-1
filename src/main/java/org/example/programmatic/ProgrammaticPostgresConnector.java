package org.example.programmatic;

import org.example.PostgresConnector;

import javax.sql.DataSource;

public class ProgrammaticPostgresConnector extends PostgresConnector {
    public ProgrammaticPostgresConnector(DataSource dataSource) {
        super(dataSource);
    }
}
