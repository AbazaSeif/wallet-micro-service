package wallet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by edgardneto on 7/13/16.
 */
class ApplicationPropertyInitializer {
    private HashMap<String, Object> propertiesMap;
    private Properties properties;
    private String filePath;

    ApplicationPropertyInitializer(String... filePath) {
        properties = new Properties();
        propertiesMap = new HashMap<>();
        this.filePath = (filePath == null) ? "" : filePath[0];
        readFromPropertyFile();
        //getPropertiesFromConfigDbService();
        //decryptProperties();
        populatePropertiesMap();

    }

    private void readFromPropertyFile() {
        try (InputStream stream = new FileInputStream(filePath)) {
            properties.load(stream);
        } catch (IOException e) {
            System.err.println("Property file not found. "+e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    private void populatePropertiesMap() {
        propertiesMap.put("server.port", "8090");
        propertiesMap.put("spring.datasource.url", "jdbc:mysql://localhost:3306/core_wallet?autoReconnect=true&useSSL=false");
        propertiesMap.put("spring.datasource.username", "root");
        propertiesMap.put("spring.datasource.password", "quirino88");
        propertiesMap.put("spring.datasource.driver-class-name", "com.mysql.jdbc.Driver");
        propertiesMap.put("spring.datasource.testWhileIdle", "true");
        propertiesMap.put("spring.datasource.validationQuery", "SELECT 1");
        propertiesMap.put("spring.jpa.show-sql", "true");
        propertiesMap.put("spring.jpa.properties.hibernate.format_sql", "true");
        propertiesMap.put("spring.jpa.hibernate.ddl-auto", "none");
        propertiesMap.put("spring.jpa.hibernate.naming-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        propertiesMap.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        propertiesMap.put("validators.jwt.secret", "32#&%+sntc3#%zqlat*qky7_2&ol=lrc34r9o4g=%jj=b1#%!@");
        propertiesMap.put("validators.jwt.algorithm", "HS256");
    }

    Map<String, Object> getPropertiesMap() {
        return propertiesMap;
    }
}

