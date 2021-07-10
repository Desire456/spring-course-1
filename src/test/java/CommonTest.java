import org.example.PostgresConnector;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class CommonTest {
    protected abstract PostgresConnector getPostgresConnector();

    @Test
    public void test() {
        PostgresConnector connector = getPostgresConnector();
        assertNotNull(connector);

        PGSimpleDataSource dataSource = (PGSimpleDataSource) connector.getDataSource();
        assertNotNull(dataSource);
        assertEquals(dataSource.getServerNames()[0], "localhost");
        assertEquals(dataSource.getPortNumbers()[0], 5432);
        assertEquals(dataSource.getUser(), "root");
        assertEquals(dataSource.getPassword(), "root");
        assertEquals(dataSource.getDatabaseName(), "postgres");
    }
}
