package nl.joelchrist.spotitube.dao.domain;

import org.bson.types.ObjectId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

public class Document {
    @MongoObjectId
    private ObjectId _id;

    @MongoObjectId
    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }
}
