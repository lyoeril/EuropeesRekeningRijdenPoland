/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class LocationTest {
    Location l1;
    Location l2;
    
    public LocationTest() {
    }
    
    @Before
    public void setUp() {
        l1 = new Location(1L, new GregorianCalendar(2010, 01, 01), 11.11, 22.22);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Location.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Location instance = l1;
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Location.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 99L;
        Location instance = l1;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getDate method, of class Location.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Location instance = l1;
        Calendar expResult = new GregorianCalendar(2010, 01, 01);
        Calendar result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class Location.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Calendar date = new GregorianCalendar(2006, 06, 06);
        Location instance = l1;
        instance.setDate(date);
        assertEquals(date, instance.getDate());
    }

    /**
     * Test of getLatitude method, of class Location.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Location instance = l1;
        double expResult = 11.11;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLatitude method, of class Location.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 0.0;
        Location instance = l1;
        instance.setLatitude(latitude);
        assertEquals(latitude, l1.getLatitude(), 0.0);
    }

    /**
     * Test of getLongitude method, of class Location.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Location instance = l1;
        double expResult = 22.22;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLongitude method, of class Location.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = 0.0;
        Location instance = new Location();
        instance.setLongitude(longitude);
        assertEquals(longitude, instance.getLongitude(), 0.0);
    }
    
}
