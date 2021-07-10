import org.example.PostgresConnector;
import org.example.xml.XMLPostgresConnector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlTest extends CommonTest {
    @Override
    protected PostgresConnector getPostgresConnector() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        return context.getBean(XMLPostgresConnector.class);
    }
}
