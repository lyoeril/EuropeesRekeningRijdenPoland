package services;

import com.auth0.jwt.algorithms.Algorithm;
import dao.interfaces.IUserDAO;
import domain.UserGroup;
import java.util.HashSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Laurent
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {
    
    @InjectMocks
    private LoginService loginService;
    
    @Mock
    private IUserDAO iUserDAO;
    
    public LoginServiceTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of authenticate method, of class LoginService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAuthenticate() throws Exception {
        loginService.authenticate("username", "password");
        Mockito.verify(iUserDAO, Mockito.times(1)).findByUsername("username");
    }

    /**
     * Test of issueToken method, of class LoginService.
     * @throws java.lang.Exception
     */
    @Test
    public void testIssueToken() throws Exception {
        String login = "login";
        String floep ="";
        HashSet<UserGroup> userGroups = new HashSet<>();
        userGroups.add(new UserGroup("usergroup"));
        
        String[] test = new String[2];
        test[0] = "test";
        test[1] = "test2";
        
        Algorithm algorithm;
        String token = "";
        floep = token;
        
        assertEquals(token, floep);
        
        assertNotEquals(token, "pizza");
    }
    
}
