package nl.joelchrist.spotitube.auth.config;

import nl.joelchrist.spotitube.auth.domain.AuthenticationToken;
import nl.joelchrist.spotitube.auth.managers.AuthenticationTokenManager;
import nl.joelchrist.spotitube.exceptions.EntityNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthenticatedFilterTest {
    @Mock
    private AuthenticationTokenManager authenticationTokenManager;

    @Mock
    private ContainerRequestContext containerRequestContext;

    @InjectMocks
    private AuthenticatedFilter authenticatedFilter;

    @Before
    public void setUp() throws Exception {
        authenticatedFilter = new AuthenticatedFilter();
        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAuthenticatedFilter() throws Exception {
        UriInfo uriInfo = mock(UriInfo.class);
        MultivaluedMap map = mock(MultivaluedMap.class);
        when(containerRequestContext.getUriInfo()).thenReturn(uriInfo);
        when(uriInfo.getQueryParameters()).thenReturn(map);
        when(map.getFirst("token")).thenReturn("1234-1234-1234");

        AuthenticationToken authenticationToken = mock(AuthenticationToken.class);
        when(authenticationToken.getExpiryDate()).thenReturn(new Date(new Date().getTime() + 6000));

        when(authenticationTokenManager.getAuthenticationTokenByToken("1234-1234-1234")).thenReturn(authenticationToken);

        authenticatedFilter.filter(containerRequestContext);

        verify(authenticationTokenManager).updateExpiryDate(authenticationToken);
    }

    @Test
    public void testNoTokenFilter() throws Exception {
        UriInfo uriInfo = mock(UriInfo.class);
        MultivaluedMap map = mock(MultivaluedMap.class);
        when(containerRequestContext.getUriInfo()).thenReturn(uriInfo);
        when(uriInfo.getQueryParameters()).thenReturn(map);
        when(map.getFirst("token")).thenReturn("1234-1234-1234");

        AuthenticationToken authenticationToken = mock(AuthenticationToken.class);
        when(authenticationToken.getExpiryDate()).thenReturn(new Date(new Date().getTime() - 6000));

        when(authenticationTokenManager.getAuthenticationTokenByToken("1234-1234-1234")).thenReturn(authenticationToken);

        authenticatedFilter.filter(containerRequestContext);

        verify(containerRequestContext).abortWith(any(Response.class));
    }

    @Test
    public void testExpiredTokenFilter() throws Exception {
        UriInfo uriInfo = mock(UriInfo.class);
        MultivaluedMap map = mock(MultivaluedMap.class);
        when(containerRequestContext.getUriInfo()).thenReturn(uriInfo);
        when(uriInfo.getQueryParameters()).thenReturn(map);
        when(map.getFirst("token")).thenReturn("1234-1234-1234");

        when(authenticationTokenManager.getAuthenticationTokenByToken("1234-1234-1234")).thenThrow(new EntityNotFoundException(AuthenticationToken.class));

        authenticatedFilter.filter(containerRequestContext);

        verify(containerRequestContext).abortWith(any(Response.class));
        verify(authenticationTokenManager).getAuthenticationTokenByToken("1234-1234-1234");
        verifyNoMoreInteractions(authenticationTokenManager);
    }
}