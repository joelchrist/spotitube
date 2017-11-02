package nl.joelchrist.spotitube.auth.managers;

import nl.joelchrist.spotitube.auth.domain.AuthenticationToken;
import nl.joelchrist.spotitube.auth.repositories.AuthenticationTokenRepository;
import nl.joelchrist.spotitube.users.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthenticationTokenManagerTest {

    @Mock
    private AuthenticationTokenRepository authenticationTokenRepository;

    @InjectMocks
    private AuthenticationTokenManager authenticationTokenManager;

    @Before
    public void setUp() throws Exception {
        authenticationTokenManager = new AuthenticationTokenManager();
        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetAuthenticationTokenByToken() throws Exception {
        AuthenticationToken authenticationToken = mock(AuthenticationToken.class);

        when(authenticationTokenRepository.getAuthenticationTokenByToken("1234-1234-1234")).thenReturn(Optional.of(authenticationToken));

        AuthenticationToken returnValue = authenticationTokenManager.getAuthenticationTokenByToken("1234-1234-1234");

        assertEquals(authenticationToken, returnValue);
    }

    @Test
    public void testGetAuthenticationTokenByUser() throws Exception {
        AuthenticationToken authenticationToken = mock(AuthenticationToken.class);

        User user = mock(User.class);

        when(authenticationTokenRepository.getAuthenticationTokenByUser(user)).thenReturn(Optional.of(authenticationToken));

        AuthenticationToken returnValue = authenticationTokenManager.getAuthenticationTokenByUser(user);

        assertEquals(authenticationToken, returnValue);
    }

    @Test
    public void generateOrUpdateExistingToken() throws Exception {
        ArgumentCaptor<AuthenticationToken> authenticationTokenArgumentCaptor = ArgumentCaptor.forClass(AuthenticationToken.class);
        ArgumentCaptor<Date> dateArgumentCaptor = ArgumentCaptor.forClass(Date.class);

        AuthenticationToken authenticationToken = mock(AuthenticationToken.class);
        User user = mock(User.class);
        when(authenticationTokenRepository.getAuthenticationTokenByUser(user)).thenReturn(Optional.of(authenticationToken));

        AuthenticationToken authenticationTokenResult = authenticationTokenManager.generateOrUpdateToken(user);

        verify(authenticationTokenRepository).updateExpiryDate(authenticationTokenArgumentCaptor.capture(), dateArgumentCaptor.capture());
        assertEquals(authenticationToken, authenticationTokenArgumentCaptor.getValue());
        assertEquals(authenticationToken, authenticationTokenResult);

    }

    @Test
    public void generateOrUpdateNonExistingToken() throws Exception {
        User user = mock(User.class);
        when(user.getUser()).thenReturn("johndoe");
        when(authenticationTokenRepository.getAuthenticationTokenByUser(user)).thenReturn(Optional.empty());

        AuthenticationToken authenticationTokenResult =  authenticationTokenManager.generateOrUpdateToken(user);

        verify(authenticationTokenRepository).saveToken(authenticationTokenResult);
    }

    @Test
    public void testUpdateExpiryDate() throws Exception {
        ArgumentCaptor<AuthenticationToken> authenticationTokenArgumentCaptor = ArgumentCaptor.forClass(AuthenticationToken.class);
        ArgumentCaptor<Date> dateArgumentCaptor = ArgumentCaptor.forClass(Date.class);
        AuthenticationToken authenticationToken = new AuthenticationToken("johndoe", "1234-1234-1234", new Date());

        authenticationTokenManager.updateExpiryDate(authenticationToken);

        verify(authenticationTokenRepository).updateExpiryDate(authenticationTokenArgumentCaptor.capture(), dateArgumentCaptor.capture());
        AuthenticationToken authenticationTokenResult = authenticationTokenArgumentCaptor.getValue();
        assertEquals(authenticationToken.getUser(), authenticationTokenResult.getUser());
        assertEquals(authenticationToken.getToken(), authenticationTokenResult.getToken());
        assertNotEquals(authenticationToken.getExpiryDate(), dateArgumentCaptor.getValue());
    }

}