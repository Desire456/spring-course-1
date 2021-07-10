import org.example.PostgresConnector;
import org.example.programmatic.ProgrammaticPostgresConnector;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.HashMap;

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
                beadDefinition -> beadDefinition.getPropertyValues().addPropertyValues(
                        new HashMap<String, Object>() {
                            {
                                put("serverNames", "${db.serverName}");
                                put("databaseName", "${db.databaseName}");
                                put("portNumbers", "${db.port}");
                                put("user", "${db.username}");
                                put("password", "${db.password}");
                            }
                        }
                ));

        context.registerBean(ProgrammaticPostgresConnector.class, new RuntimeBeanReference("dataSource"));

        context.refresh();

        return context.getBean(ProgrammaticPostgresConnector.class);
    }
}
