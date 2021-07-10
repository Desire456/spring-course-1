import org.example.PostgresConnector;
import org.example.java.JavaConfiguration;
import org.example.java.JavaPostgresConnector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaTest extends CommonTest {
    @Override
    protected PostgresConnector getPostgresConnector() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);

        return context.getBean(JavaPostgresConnector.class);
    }
}
