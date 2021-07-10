package org.example.xml;

import org.example.PostgresConnector;

import javax.sql.DataSource;

public class XMLPostgresConnector extends PostgresConnector {
    public XMLPostgresConnector(DataSource dataSource) {
        super(dataSource);
    }
}
