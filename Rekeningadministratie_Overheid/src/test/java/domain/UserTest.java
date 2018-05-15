/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class UserTest {
    User u1;
    User u2;
    
    public UserTest() {
    }
    
    @Before
    public void setUp() {
        u1 = new User("email1", "username1", "password1", "picture1", "firstname1", "lastname1", "location1");
        u1.setId(1L);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        User instance = u1;
        Long expResult = 1L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPicture method, of class User.
     */
    @Test
    public void testGetPicture() {
        System.out.println("getPicture");
        User instance = u1;
        String expResult = "picture1";
        String result = instance.getPicture();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirstName method, of class User.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        User instance = u1;
        String expResult = "firstname1";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastName method, of class User.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        User instance = u1;
        String expResult = "lastname1";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLocation method, of class User.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        User instance = u1;
        String expResult = "location1";
        String result = instance.getLocation();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        User instance = u1;
        String expResult = "email1";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = u1;
        String expResult = "password1";
        String result = instance.getPassword();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getGroups method, of class User.
     */
    @Test
    public void testGetGroups() {
        System.out.println("getGroups");
        User instance = u1;
        Set<UserGroup> expResult = new HashSet<>();
        Set<UserGroup> result = instance.getGroups();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGroups method, of class User.
     */
    @Test
    public void testSetGroups() {
        System.out.println("setGroups");
        Set<UserGroup> groups = new HashSet<>();
        groups.add(new UserGroup("testGroup"));
        User instance = u1;
        instance.setGroups(groups);
        assertEquals(groups, instance.getGroups());
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User instance = u1;
        String expResult = "username1";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "email1";
        User instance = u1;
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of setId method, of class User.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = 99L;
        User instance = u1;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "username99";
        User instance = u1;
        instance.setUsername(username);
        assertEquals(username, instance.getUsername());
    }

    /**
     * Test of setPicture method, of class User.
     */
    @Test
    public void testSetPicture() {
        System.out.println("setPicture");
        String picture = "picture99";
        User instance = u1;
        instance.setPicture(picture);
        assertEquals(picture, instance.getPicture());
    }

    /**
     * Test of setFirstName method, of class User.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "firstname99";
        User instance = u1;
        instance.setFirstName(firstName);
        assertEquals(firstName, instance.getFirstName());
    }

    /**
     * Test of setLastName method, of class User.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "lastname99";
        User instance = u1;
        instance.setLastName(lastName);
        assertEquals(lastName, instance.getLastName());
    }

    /**
     * Test of setLocation method, of class User.
     */
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        String location = "location99";
        User instance = u1;
        instance.setLocation(location);
        assertEquals(location, instance.getLocation());
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "password99";
        User instance = u1;
        instance.setPassword(password);
        assertFalse(instance.getPassword().equals(password));
    }

    /**
     * Test of getStringGroups method, of class User.
     */
    @Test
    public void testGetStringGroups() {
        System.out.println("getStringGroups");
        User instance = u1;
        instance.addGroup(new UserGroup("testGroup"));
        String expResult = "testGroup";
        assertTrue(instance.getStringGroups().contains(expResult));
    }

    /**
     * Test of addGroup method, of class User.
     */
    @Test
    public void testAddGroup() {
        System.out.println("addGroup");
        UserGroup group = new UserGroup("test");
        User instance = u1;
        instance.addGroup(group);
        assertTrue(instance.getGroups().contains(group));
    }

    /**
     * Test of removeGroup method, of class User.
     */
    @Test
    public void testRemoveGroup() {
        System.out.println("removeGroup");
        UserGroup group = new UserGroup("testGroup");
        User instance = u1;
        instance.addGroup(group);
        assertTrue(instance.getGroups().contains(group));
        
        instance.removeGroup(group);
        assertFalse(instance.getGroups().contains(group));
    }

//    /**
//     * Test of hashCode method, of class User.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        User instance = u1;
//        instance.hashCode();
//        int expResult = 1;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);;
//    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        User instance2 = u2;
        User instance = u1;
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }
    
}
