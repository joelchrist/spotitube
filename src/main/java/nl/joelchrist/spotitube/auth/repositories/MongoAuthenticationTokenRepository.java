package nl.joelchrist.spotitube.auth.repositories;

import nl.joelchrist.spotitube.auth.domain.AuthenticationToken;
import nl.joelchrist.spotitube.dao.Repository;
import nl.joelchrist.spotitube.users.domain.User;
import org.jongo.MongoCollection;

import javax.enterprise.inject.Default;
import java.util.Date;
import java.util.Optional;

@Default
public class MongoAuthenticationTokenRepository extends Repository implements AuthenticationTokenRepository {
    private MongoCollection authenticationTokenCollection = getCollection("authenticationTokens");

    @Override
    public Optional<AuthenticationToken> getAuthenticationTokenByToken(String token) {
        AuthenticationToken foundToken = authenticationTokenCollection.findOne("{token: #}", token).as(AuthenticationToken.class);
        return Optional.ofNullable(foundToken);
    }

    @Override
    public void saveToken(AuthenticationToken token) {
        authenticationTokenCollection.save(token);
    }

    @Override
    public Optional<AuthenticationToken> getAuthenticationTokenByUser(User user) {
        AuthenticationToken foundToken = authenticationTokenCollection.findOne("{user: #}", user.getUser()).as(AuthenticationToken.class);
        return Optional.ofNullable(foundToken);
    }

    @Override
    public void updateExpiryDate(AuthenticationToken authenticationToken, Date expiryDate) {
        authenticationToken.setExpiryDate(expiryDate);
        authenticationTokenCollection.update(authenticationToken.getId()).with(authenticationToken);
    }
}
