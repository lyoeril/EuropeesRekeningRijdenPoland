/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.Invoice;
import domain.Rekeningrijder;
import domain.UserGroup;
import domain.Vehicle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class DTO_RekeningrijderTest {
    
    public DTO_RekeningrijderTest() {
    }
    
    DTO_Rekeningrijder dtoRekeningrijder;
    Rekeningrijder rekeningrijder;
    
    private long id;
    private String address;
    private String city;
    private String email;
    private Set<UserGroup> groups;
    private List<Invoice> invoices;
    private List<Vehicle> ownedVehicles;
    private String username;
    private String password;
    
    
    
    @Before
    public void setUp() {
        id = 1L;
        address = "address";
        city = "city";
        email = "email";
        groups = new HashSet<>();
        invoices = new ArrayList<>();
        ownedVehicles = new ArrayList<>();
        username = "username";
        password = "password";
        rekeningrijder = new Rekeningrijder(username, password, address, city, email);
        rekeningrijder.setId(id);
        
        dtoRekeningrijder = new DTO_Rekeningrijder(rekeningrijder);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class DTO_Rekeningrijder.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DTO_Rekeningrijder instance = dtoRekeningrijder;
        long expResult = id;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class DTO_Rekeningrijder.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 2L;
        DTO_Rekeningrijder instance = dtoRekeningrijder;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getUsername method, of class DTO_Rekeningrijder.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        DTO_Rekeningrijder instance = dtoRekeningrijder;
        String expResult = username;
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class DTO_Rekeningrijder.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "newUsername";
        DTO_Rekeningrijder instance = dtoRekeningrijder;
        instance.setUsername(username);
        assertEquals(username, instance.getUsername());
    }

    /**
     * Test of getAddress method, of class DTO_Rekeningrijder.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        DTO_Rekeningrijder instance = dtoRekeningrijder;
        String expResult = address;
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class DTO_Rekeningrijder.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "newAddress";
        DTO_Rekeningrijder instance = dtoRekeningrijder;
        instance.setAddress(address);
        assertEquals(address, instance.getAddress());
    }

    /**
     * Test of getEmail method, of class DTO_Rekeningrijder.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        DTO_Rekeningrijder instance = dtoRekeningrijder;
        String expResult = email;
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class DTO_Rekeningrijder.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "newEmail";
        DTO_Rekeningrijder instance = dtoRekeningrijder;
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }
    
}
