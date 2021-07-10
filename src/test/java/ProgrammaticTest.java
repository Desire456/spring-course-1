import org.example.PostgresConnector;
import org.example.programmatic.ProgrammaticPostgresConnector;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

public class ProgrammaticTest extends CommonTest {
    @Override
    protected PostgresConnector getPostgresConnector() {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocations(new ClassPathResource("db.properties"));
            return configurer;
        });

        context.registerBean(PGSimpleDataSource.class, () -> {
            PropertySource<?> propertySource = context.getBean(PropertySourcesPlaceholderConfigurer.class)
                    .getAppliedPropertySources()
                    .get("localProperties");
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setServerNames(new String[]{(String) propertySource.getProperty("db.serverName")});
            dataSource.setDatabaseName((String) propertySource.getProperty("db.databaseName"));
            dataSource.setPortNumbers(new int[]{Integer.parseInt((String) propertySource.getProperty("db.port"))});
            dataSource.setUser((String) propertySource.getProperty("db.username"));
            dataSource.setPassword((String) propertySource.getProperty("db.password"));
            return dataSource;
        });

        context.registerBean(ProgrammaticPostgresConnector.class, () -> {
            DataSource dataSource = context.getBean(DataSource.class);
            return new ProgrammaticPostgresConnector(dataSource);
        });

        context.refresh();

        return context.getBean(ProgrammaticPostgresConnector.class);
    }
}
