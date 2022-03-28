package fontys.customerdbdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

/**
 *
 * @author hvd
 */
public class DBProvider {

    static Map<String, DataSource> cache = new HashMap<>();

    static DataSource getDataSource(final String sourceName) {

        return cache.computeIfAbsent(sourceName,
                (s) -> {
                    Properties props = properties("application.properties");

                    PGSimpleDataSource source = new PGSimpleDataSource();

                    String prefix = sourceName + ".jdbc.";

                    String[] serverNames = {props.getProperty(prefix + "dbhost")};

                    source.setServerNames(serverNames);
                    source.setUser(props.getProperty(prefix + "username"));
                    source.setDatabaseName(props.getProperty(prefix + "dbname"));
                    source.setPassword(props.getProperty(prefix + "password"));
                    source.setCurrentSchema(props.getProperty(prefix + "schema"));

                    return source;
                }
        );
    }

    static Properties properties(String propFileName) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(propFileName);) {
            properties.load(fis);
        } catch (IOException ignored) {
            Logger.getLogger(DBProvider.class.getName()).log(
                    Level.INFO,
                    "attempt to read file from well known location failed'",
                    ignored);
        }
        return properties;
    }

}
