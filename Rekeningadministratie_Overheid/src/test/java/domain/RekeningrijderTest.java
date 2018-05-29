/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import enums.VehicleType;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class RekeningrijderTest {
    Rekeningrijder r1;
    Rekeningrijder r2;
    
    public RekeningrijderTest() {
    }
    
    @Before
    public void setUp() {
        r1 = new Rekeningrijder("username1", "password1", "address1", "city", "email1");
        r1.setId(1L);
        r2 = new Rekeningrijder("username2", "password2", "address2", "city", "email2");
        r2.setId(2L);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Rekeningrijder.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Rekeningrijder instance = r1;
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Rekeningrijder.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 99L;
        Rekeningrijder instance = r1;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getName method, of class Rekeningrijder.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getName");
        Rekeningrijder instance = r1;
        String expResult = "username1";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Rekeningrijder.
     */
    @Test
    public void testSetusername() {
        System.out.println("setName");
        String name = "name99";
        Rekeningrijder instance = r1;
        instance.setUsername(name);
        assertEquals(name, instance.getUsername());
    }

    /**
     * Test of getAddress method, of class Rekeningrijder.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Rekeningrijder instance = r1;
        String expResult = "address1";
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Rekeningrijder.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "address99";
        Rekeningrijder instance = r1;
        instance.setAddress(address);
        assertEquals(address, instance.getAddress());
        
    }
    
    /**
     * Test of getInvoices method, of class Rekeningrijder.
     */
    @Test
    public void testGetInvoices() {
        System.out.println("getInvoices");
        Rekeningrijder instance = r1;
        List<Invoice> expResult = new ArrayList<Invoice>();
        List<Invoice> result = instance.getInvoices();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInvoices method, of class Rekeningrijder.
     */
    @Test
    public void testSetInvoices() {
        System.out.println("setInvoices");
        List<Invoice> invoices = new ArrayList<Invoice>();
        invoices.add(new Invoice(1L, 0.2, new GregorianCalendar(), r1));
        Rekeningrijder instance = r1;
        instance.setInvoices(invoices);
    }

    /**
     * Test of getOwnedVehicles method, of class Rekeningrijder.
     */
    @Test
    public void testGetOwnedVehicles() {
        System.out.println("getOwnedVehicles");
        Rekeningrijder instance = r1;
        List<Vehicle> expResult = new ArrayList<Vehicle>();
        List<Vehicle> result = instance.getOwnedVehicles();
        assertEquals(expResult, result);
    }
    
}
