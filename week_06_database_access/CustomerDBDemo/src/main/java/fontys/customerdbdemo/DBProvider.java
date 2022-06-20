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

    static Map<String, DataSource> cache = new HashMap<>();  // it is stored in cache 
// create dataSource - part of JDBS
    
    // this return a data source and cache  the config is taken from the HashMap
    
    static DataSource getDataSource(final String sourceName) {

        return cache.computeIfAbsent(sourceName,
                (s) -> {
                    Properties props = properties("application.properties"); // want to read from a properties file which is in the root of our prj like the pom file
// create the impl of dataSource - it can be impl cause we provided the api in the pom file
                    PGSimpleDataSource source = new PGSimpleDataSource(); // the con. the specific impl of a data source

                    String prefix = sourceName + ".jdbc.";

                    String[] serverNames = {props.getProperty(prefix + "dbhost")};
// config our data source
                    source.setServerNames(serverNames);
                    source.setUser(props.getProperty(prefix + "username"));
                    source.setDatabaseName(props.getProperty(prefix + "dbname"));
                    source.setPassword(props.getProperty(prefix + "password"));
                    source.setCurrentSchema(props.getProperty(prefix + "schema"));

                    return source;
                }
        );
    }

    static Properties properties(String propFileName) { // reading the file
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
