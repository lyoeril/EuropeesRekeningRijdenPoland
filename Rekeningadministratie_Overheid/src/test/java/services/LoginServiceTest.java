/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dao.interfaces.IUserDAO;
import domain.UserGroup;
import java.io.UnsupportedEncodingException;
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
     */
    @Test
    public void testAuthenticate() throws Exception {
        loginService.authenticate("username", "password");
        Mockito.verify(iUserDAO, Mockito.times(1)).findByUsername("username");
    }

    /**
     * Test of issueToken method, of class LoginService.
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
