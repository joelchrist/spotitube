package nl.joelchrist.spotitube.users.repositories;

import nl.joelchrist.spotitube.users.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUser(String user);
}
