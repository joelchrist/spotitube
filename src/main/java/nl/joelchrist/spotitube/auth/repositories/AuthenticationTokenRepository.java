package nl.joelchrist.spotitube.auth.repositories;

import nl.joelchrist.spotitube.auth.domain.AuthenticationToken;
import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.users.domain.User;

import java.util.Date;
import java.util.Optional;

public interface AuthenticationTokenRepository {
    Optional<AuthenticationToken> getAuthenticationTokenByToken(String token);

    void saveToken(AuthenticationToken token);

    Optional<AuthenticationToken> getAuthenticationTokenByUser(User user);

    void updateExpiryDate(AuthenticationToken authenticationToken, Date expiryDate);
}
