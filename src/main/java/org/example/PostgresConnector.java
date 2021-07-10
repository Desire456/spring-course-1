package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.sql.DataSource;

@AllArgsConstructor
@Getter
public class PostgresConnector {
    private DataSource dataSource;
}
