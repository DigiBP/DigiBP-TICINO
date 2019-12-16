package ch.fhnw.digibp.config.yaml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("database")
public class DatabaseProperties {
    private String connection;

    public String getConnection()
    {
        return connection;
    }

    public void setConnection( String connection )
    {
        this.connection = connection;
    }
}