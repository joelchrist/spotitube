package nl.joelchrist.spotitube.dao;

import com.mongodb.Mongo;
import com.mongodb.MongoClientURI;
import nl.joelchrist.spotitube.dao.util.DatabaseProperties;
import nl.joelchrist.spotitube.users.domain.User;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Repository {
    private DatabaseProperties databaseProperties;
    private Jongo jongo;

    public Repository() {
        this.databaseProperties = new DatabaseProperties();
        Mongo mongo = Mongo.Holder.singleton().connect(new MongoClientURI(databaseProperties.getUrl()));
        this.jongo = new Jongo(mongo.getDB("spotitube"));
    }

    protected MongoCollection getCollection(String collectionName) {
        return jongo.getCollection(collectionName);
    }
}
