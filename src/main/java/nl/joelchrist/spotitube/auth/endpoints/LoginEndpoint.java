package nl.joelchrist.spotitube.auth.endpoints;

import nl.joelchrist.spotitube.auth.config.Authenticated;
import nl.joelchrist.spotitube.auth.domain.LoginCredentials;
import nl.joelchrist.spotitube.auth.domain.LoginResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class LoginEndpoint {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginResponse login(LoginCredentials loginCredentials) {
        return new LoginResponse(loginCredentials.getUser(), "1234-1234-1234");
    }
}
