package nl.joelchrist.spotitube.users.repositories;

import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.users.domain.User;
import org.jongo.MongoCollection;

import javax.enterprise.inject.Default;
import java.util.Optional;

@Default
public class MongoUserRepository extends Repository implements UserRepository {
    private MongoCollection userCollection = getCollection("users");

    @Override
    public Optional<User> getUser(String user) {
        User foundUser = userCollection.findOne("{user: #}", user).as(User.class);
        return Optional.ofNullable(foundUser);
    }
}
