package nl.joelchrist.spotitube.util;

import nl.joelchrist.spotitube.auth.managers.AuthenticationTokenManager;
import nl.joelchrist.spotitube.users.managers.UserManager;

import javax.inject.Inject;

public class AuthenticationHelper {
    @Inject
    private AuthenticationTokenManager authenticationTokenManager;

    @Inject
    private UserManager userManager;
}
