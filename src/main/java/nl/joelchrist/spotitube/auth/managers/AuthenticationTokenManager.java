package nl.joelchrist.spotitube.auth.managers;

import nl.joelchrist.spotitube.auth.domain.AuthenticationToken;
import nl.joelchrist.spotitube.auth.repositories.AuthenticationTokenRepository;
import nl.joelchrist.spotitube.exceptions.EntityNotFoundException;
import nl.joelchrist.spotitube.users.domain.User;

import javax.inject.Inject;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

public class AuthenticationTokenManager {
    public static final long HOUR = 3600*1000; // in milli-seconds.


    @Inject
    private AuthenticationTokenRepository authenticationTokenRepository;

    public AuthenticationToken getAuthenticationTokenByToken(String token) throws EntityNotFoundException {
        Optional<AuthenticationToken> authenticationTokenOptional = authenticationTokenRepository.getAuthenticationTokenByToken(token);
        return authenticationTokenOptional.orElseThrow(() -> new EntityNotFoundException(AuthenticationToken.class));
    }

    public AuthenticationToken getAuthenticationTokenByUser(User user) throws EntityNotFoundException {
        Optional<AuthenticationToken> authenticationTokenOptional = authenticationTokenRepository.getAuthenticationTokenByUser(user);
        return authenticationTokenOptional.orElseThrow(() -> new EntityNotFoundException(AuthenticationToken.class));
    }

    public AuthenticationToken generateOrUpdateToken(User user) {
        AuthenticationToken authenticationToken;
        try {
            authenticationToken = getAuthenticationTokenByUser(user);
            updateExpiryDate(authenticationToken);

        } catch (EntityNotFoundException e) {
            authenticationToken = generateAndSaveToken(user.getUser());
        }
        return authenticationToken;
    }

    public void updateExpiryDate(AuthenticationToken authenticationToken) {
        Date expiryDate = new Date(new Date().getTime() + HOUR * 2);

        authenticationTokenRepository.updateExpiryDate(authenticationToken, expiryDate);
    }

    private AuthenticationToken generateAndSaveToken(String user) {
        Date expiryDate = new Date(new Date().getTime() + HOUR * 2);
        String token = generateNewToken();
        AuthenticationToken authenticationToken = new AuthenticationToken(user, token, expiryDate);
        authenticationTokenRepository.saveToken(authenticationToken);
        return authenticationToken;
    }

    private String generateNewToken() {
        Random random = new Random();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String randomNumber = String.format("%04d", random.nextInt(10000));
            token.append(randomNumber);
            token.append("-");
        }
        token.deleteCharAt(14);
        return token.toString();
    }
}
