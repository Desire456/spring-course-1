import org.example.PostgresConnector;
import org.example.annotation.AnnotationPostgresConnector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationTest extends CommonTest {
    @Override
    protected PostgresConnector getPostgresConnector() {
        ApplicationContext context = new AnnotationConfigApplicationContext("org.example.annotation");

        return context.getBean(AnnotationPostgresConnector.class);
    }
}
