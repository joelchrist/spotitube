package nl.joelchrist.spotitube.auth.config;

import nl.joelchrist.spotitube.auth.domain.AuthenticationToken;
import nl.joelchrist.spotitube.auth.managers.AuthenticationTokenManager;
import nl.joelchrist.spotitube.exceptions.EntityNotFoundException;
import nl.joelchrist.spotitube.users.domain.User;
import nl.joelchrist.spotitube.users.managers.UserManager;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Date;

@Provider
@Authenticated
@Priority(Priorities.AUTHENTICATION)
public class AuthenticatedFilter implements ContainerRequestFilter {

    @Inject
    private AuthenticationTokenManager authenticationTokenManager;

    @Inject
    private UserManager userManager;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String token = containerRequestContext.getUriInfo().getQueryParameters().getFirst("token");
        try {
            AuthenticationToken authenticationToken = authenticationTokenManager.getAuthenticationTokenByToken(token);
            if (authenticationToken.getExpiryDate().before(new Date())) {
                unAuthorizeRequest(containerRequestContext);
                return;
            }
            authenticationTokenManager.updateExpiryDate(authenticationToken);
            User currentUser = userManager.getUser(authenticationToken.getUser());

            containerRequestContext.setProperty("currentUser", currentUser);
        } catch (EntityNotFoundException e) {
            unAuthorizeRequest(containerRequestContext);
        }
    }

    private void unAuthorizeRequest(ContainerRequestContext containerRequestContext) {
        Response response = Response.status(403).build();
        containerRequestContext.abortWith(response);
    }
}