package nl.joelchrist.spotitube.auth.endpoints;

import nl.joelchrist.spotitube.auth.domain.AuthenticationToken;
import nl.joelchrist.spotitube.auth.domain.LoginCredentials;
import nl.joelchrist.spotitube.auth.managers.AuthenticationTokenManager;
import nl.joelchrist.spotitube.auth.rest.RestAuthenticationToken;
import nl.joelchrist.spotitube.auth.rest.RestAuthenticationTokenMapper;
import nl.joelchrist.spotitube.exceptions.EntityNotFoundException;
import nl.joelchrist.spotitube.users.domain.User;
import nl.joelchrist.spotitube.users.managers.UserManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoginEndpointTest {
    @Mock
    private UserManager userManager;

    @Mock
    private AuthenticationTokenManager authenticationTokenManager;

    @Mock
    private RestAuthenticationTokenMapper restAuthenticationTokenMapper;

    @InjectMocks
    private LoginEndpoint loginEndpoint;

    @Before
    public void setUp() throws Exception {
        loginEndpoint = new LoginEndpoint();
        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testValidLogin() throws EntityNotFoundException {
        User user = mock(User.class);
        when(user.getUser()).thenReturn("johndoe");
        when(user.getPassword()).thenReturn("hunter2");

        when(userManager.getUser("johndoe")).thenReturn(user);

        AuthenticationToken authenticationToken = mock(AuthenticationToken.class);

        RestAuthenticationToken restAuthenticationToken = mock(RestAuthenticationToken.class);

        LoginCredentials loginCredentials = new LoginCredentials("johndoe", "hunter2");

        when(authenticationTokenManager.generateOrUpdateToken(user)).thenReturn(authenticationToken);
        when(restAuthenticationTokenMapper.toRest(authenticationToken)).thenReturn(restAuthenticationToken);

        Response response = loginEndpoint.login(loginCredentials);
        assertEquals(200, response.getStatus());
        assertEquals(restAuthenticationToken, response.getEntity());
    }

    @Test
    public void testInvalidPasswordLogin() throws EntityNotFoundException {
        User user = mock(User.class);
        when(user.getUser()).thenReturn("johndoe");
        when(user.getPassword()).thenReturn("hunter2");

        when(userManager.getUser("johndoe")).thenReturn(user);

        LoginCredentials loginCredentials = new LoginCredentials("johndoe", "wrongpass");

        Response response = loginEndpoint.login(loginCredentials);

        assertEquals(403, response.getStatus());
    }
}
