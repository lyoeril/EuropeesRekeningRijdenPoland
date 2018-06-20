/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class UserGroupTest {
    UserGroup ug1;
    User u1;
    
    public UserGroupTest() {
    }
    
    @Before
    public void setUp() {
        ug1 = new UserGroup("usergroup1");
        u1 = new User("email1", "username1", "password1");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getGroupName method, of class UserGroup.
     */
    @Test
    public void testGetGroupName() {
        System.out.println("getGroupName");
        UserGroup instance = ug1;
        String expResult = "usergroup1";
        String result = instance.getGroupName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGroupName method, of class UserGroup.
     */
    @Test
    public void testSetGroupName() {
        System.out.println("setGroupName");
        String usergroup = "newGroupName";
        UserGroup instance = ug1;
        instance.setGroupName(usergroup);
        assertEquals(usergroup, instance.getGroupName());
    }

    /**
     * Test of getUsers method, of class UserGroup.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        UserGroup instance = ug1;
        Set<User> expResult = new HashSet<>();
        Set<User> result = instance.getUsers();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsers method, of class UserGroup.
     */
    @Test
    public void testSetUsers() {
        System.out.println("setUsers");
        Set<User> users = new HashSet<>();
        users.add(u1);
        UserGroup instance = ug1;
        instance.setUsers(users);  
        assertEquals(users, instance.getUsers());
    }

    /**
     * Test of addUser method, of class UserGroup.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User user = u1;
        UserGroup instance = ug1;
        instance.addUser(user);
        assertTrue(instance.getUsers().contains(user));
    }

    /**
     * Test of removeUser method, of class UserGroup.
     */
    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        User user = u1;
        UserGroup instance = ug1;
        instance.addUser(u1);
        assertTrue(instance.getUsers().contains(u1));
        instance.removeUser(user);
        assertFalse(instance.getUsers().contains(u1));
    }

    /**
     * Test of toString method, of class UserGroup.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        UserGroup instance = ug1;
        String expResult = "usergroup1";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class UserGroup.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        UserGroup instance = ug1;
        UserGroup instance2 = new UserGroup("test");
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }
    
}
