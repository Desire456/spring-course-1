import org.example.PostgresConnector;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import static org.junit.jupiter.api.Assertions.*;

public abstract class CommonTest {
    protected abstract PostgresConnector getPostgresConnector();

    @Test
    public void test() {
        PostgresConnector connector = getPostgresConnector();
        assertNotNull(connector);

        PGSimpleDataSource dataSource = (PGSimpleDataSource) connector.getDataSource();
        assertNotNull(dataSource);
        assertArrayEquals(dataSource.getServerNames(), new String[]{"localhost"});
        assertArrayEquals(dataSource.getPortNumbers(), new int[]{5432});
        assertEquals(dataSource.getUser(), "root");
        assertEquals(dataSource.getPassword(), "root");
        assertEquals(dataSource.getDatabaseName(), "postgres");
    }
}
