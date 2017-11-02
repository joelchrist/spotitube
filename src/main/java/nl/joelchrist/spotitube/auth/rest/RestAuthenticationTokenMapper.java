package nl.joelchrist.spotitube.auth.rest;

import nl.joelchrist.spotitube.auth.domain.AuthenticationToken;
import nl.joelchrist.spotitube.util.RestMapper;

import java.sql.Date;

public class RestAuthenticationTokenMapper implements RestMapper<RestAuthenticationToken,AuthenticationToken> {
    @Override
    public RestAuthenticationToken toRest(AuthenticationToken nonRest) {
        return new RestAuthenticationToken(nonRest.getUser(), nonRest.getToken());
    }

    @Override
    public AuthenticationToken fromRest(RestAuthenticationToken rest) {
        return new AuthenticationToken(rest.getUser(), rest.getToken(), new Date(Long.MAX_VALUE));
    }
}
