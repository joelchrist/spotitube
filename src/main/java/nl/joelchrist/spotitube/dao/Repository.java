package nl.joelchrist.spotitube.dao;

import nl.joelchrist.spotitube.dao.util.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Repository {
    private Logger logger = Logger.getLogger(getClass().getName());
    private DatabaseProperties databaseProperties;

    public Repository() {
        this.databaseProperties = new DatabaseProperties();
        tryLoadJdbcDriver(databaseProperties);
    }

    private void tryLoadJdbcDriver(DatabaseProperties databaseProperties) {
        try {
            Class.forName(databaseProperties.getDriver());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Can't load JDBC Driver " + databaseProperties.getDriver(), e);
        }
    }

    protected Connection getConnection() throws SQLException {
        String url = databaseProperties.getUrl();
        String user = databaseProperties.getUser();
        String password = databaseProperties.getPassword();
        return DriverManager.getConnection(url, user, password);
    }
}
