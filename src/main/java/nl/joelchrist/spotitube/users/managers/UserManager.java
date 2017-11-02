package nl.joelchrist.spotitube.users.managers;

import nl.joelchrist.spotitube.exceptions.EntityNotFoundException;
import nl.joelchrist.spotitube.users.domain.User;
import nl.joelchrist.spotitube.users.repositories.UserRepository;

import javax.inject.Inject;
import java.util.Optional;

public class UserManager {
    @Inject
    private UserRepository userRepository;

    public User getUser(String user) throws EntityNotFoundException {
        Optional<User> userOptional = userRepository.getUser(user);
        return userOptional.orElseThrow(() -> new EntityNotFoundException(User.class));
    }
}
