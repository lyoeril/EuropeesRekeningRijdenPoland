/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class DTO_UserTest {
    
    public DTO_UserTest() {
    }
    
    private User user;
    DTO_User dtoUser;
    
    private long id;
    private String username;
    private String password;
    private String email;
    
    
    @Before
    public void setUp() {
        username = "username";
        password = "password";
        email = "email";
        user = new User(username, password, email);
        user.setId(id);
        
        dtoUser = new DTO_User(user);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class DTO_User.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DTO_User instance = dtoUser;
        long expResult = id;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class DTO_User.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 1L;
        DTO_User instance = dtoUser;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getUsername method, of class DTO_User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        DTO_User instance = dtoUser;
        String expResult = username;
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class DTO_User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "newUsername";
        DTO_User instance = dtoUser;
        instance.setUsername(username);
        assertEquals(username, instance.getUsername());
    }

    /**
     * Test of getEmail method, of class DTO_User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        DTO_User instance = dtoUser;
        String expResult = email;
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class DTO_User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "newEmail";
        DTO_User instance = dtoUser;
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }
    
}
