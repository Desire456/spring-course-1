package org.example.java;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
public class JavaConfiguration {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(new ClassPathResource("db.properties"));
        return configurer;
    }

    @Bean
    public DataSource dataSource(@Value("${db.serverName}") String serverName,
                                 @Value("${db.databaseName}") String databaseName,
                                 @Value("${db.port}") int portNumber,
                                 @Value("${db.username}") String user,
                                 @Value("${db.password}") String password) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{serverName});
        dataSource.setDatabaseName(databaseName);
        dataSource.setPortNumbers(new int[]{portNumber});
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JavaPostgresConnector javaPostgresConnector(DataSource dataSource) {
        return new JavaPostgresConnector(dataSource);
    }
}
