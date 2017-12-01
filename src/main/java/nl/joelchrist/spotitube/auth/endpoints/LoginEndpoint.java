package nl.joelchrist.spotitube.auth.endpoints;

import nl.joelchrist.spotitube.auth.config.Authenticated;
import nl.joelchrist.spotitube.auth.domain.AuthenticationToken;
import nl.joelchrist.spotitube.auth.domain.LoginCredentials;
import nl.joelchrist.spotitube.auth.managers.AuthenticationTokenManager;
import nl.joelchrist.spotitube.auth.rest.RestAuthenticationToken;
import nl.joelchrist.spotitube.auth.rest.RestAuthenticationTokenMapper;
import nl.joelchrist.spotitube.exceptions.EntityNotFoundException;
import nl.joelchrist.spotitube.users.domain.User;
import nl.joelchrist.spotitube.users.managers.UserManager;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginEndpoint {

    @Inject
    private UserManager userManager;

    @Inject
    private AuthenticationTokenManager authenticationTokenManager;

    @Inject
    private RestAuthenticationTokenMapper restAuthenticationTokenMapper;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginCredentials loginCredentials) {
        try {
            User user = userManager.getUser(loginCredentials.getUser());

            if (!user.getPassword().equals(loginCredentials.getPassword())) {
                return Response.status(403).build();
            }

            AuthenticationToken authenticationToken = authenticationTokenManager.generateOrUpdateToken(user);
            RestAuthenticationToken restAuthenticationToken = restAuthenticationTokenMapper.toRest(authenticationToken);
            return Response.status(200).entity(restAuthenticationToken).build();

        } catch (EntityNotFoundException e) {
            return Response.status(403).build();
        }
    }
}
