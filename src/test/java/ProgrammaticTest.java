import org.example.PostgresConnector;
import org.example.programmatic.ProgrammaticPostgresConnector;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.HashMap;
import java.util.Map;

public class ProgrammaticTest extends CommonTest {
    @Override
    protected PostgresConnector getPostgresConnector() {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocations(new ClassPathResource("db.properties"));
            return configurer;
        });

        context.registerBean("dataSource", PGSimpleDataSource.class,
                beadDefinition -> beadDefinition.getPropertyValues().addPropertyValues(getDbPropertiesMap()));

        context.registerBean(ProgrammaticPostgresConnector.class, new RuntimeBeanReference("dataSource"));

        context.refresh();

        return context.getBean(ProgrammaticPostgresConnector.class);
    }

    private Map<String, Object> getDbPropertiesMap() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("serverNames", "${db.serverName}");
        properties.put("databaseName", "${db.databaseName}");
        properties.put("portNumbers", "${db.port}");
        properties.put("user", "${db.username}");
        properties.put("password", "${db.password}");
        return properties;
    }
}
