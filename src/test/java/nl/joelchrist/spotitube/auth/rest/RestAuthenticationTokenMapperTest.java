package nl.joelchrist.spotitube.auth.rest;

import nl.joelchrist.spotitube.auth.domain.AuthenticationToken;
import nl.joelchrist.spotitube.util.RestMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestAuthenticationTokenMapperTest {

    @InjectMocks
    RestMapper<RestAuthenticationToken, AuthenticationToken> restAuthenticationTokenMapper;

    @Before
    public void setUp() throws Exception {
        restAuthenticationTokenMapper = new RestAuthenticationTokenMapper();
        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToRest() throws Exception {
        AuthenticationToken authenticationToken = new AuthenticationToken("johndoe", "1234-1234-1234", new Date());
        RestAuthenticationToken restAuthenticationToken = restAuthenticationTokenMapper.toRest(authenticationToken);

        assertEquals(restAuthenticationToken.getToken(), authenticationToken.getToken());
        assertEquals(restAuthenticationToken.getUser(), authenticationToken.getUser());

    }

    @Test
    public void testFromRest() throws Exception {
        RestAuthenticationToken restAuthenticationToken = new RestAuthenticationToken("johndoe", "1234-1234-1234");
        AuthenticationToken authenticationToken = restAuthenticationTokenMapper.fromRest(restAuthenticationToken);

        assertEquals(authenticationToken.getToken(), restAuthenticationToken.getToken());
        assertEquals(authenticationToken.getUser(), restAuthenticationToken.getUser());
    }

}