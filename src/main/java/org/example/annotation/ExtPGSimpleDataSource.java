package org.example.annotation;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExtPGSimpleDataSource extends PGSimpleDataSource {

    public ExtPGSimpleDataSource(@Value("${db.serverName}") String serverName,
                                 @Value("${db.databaseName}") String databaseName,
                                 @Value("${db.port}") int portNumber,
                                 @Value("${db.username}") String user,
                                 @Value("${db.password}") String password) {
        super.setServerNames(new String[]{serverName});
        super.setDatabaseName(databaseName);
        super.setPortNumbers(new int[]{portNumber});
        super.setUser(user);
        super.setPassword(password);
    }
}
