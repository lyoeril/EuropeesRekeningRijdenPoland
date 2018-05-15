/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Calendar;
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
public class RideTest {
    
    Ride r1;
    Ride r2;
    
    Calendar cStart;
    Calendar cEnd;
    
    Location l1;
    Location l2;
    
    public RideTest() {
    }
    
    @Before
    public void setUp() {
        cStart = new GregorianCalendar(2010, 01, 01);
        cEnd = new GregorianCalendar(2011, 11, 11);
        r1 = new Ride(1L, cStart, cEnd);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Ride.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Ride instance = r1;
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Ride.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 99L;
        Ride instance = r1;
        instance.setId(id);
    }

    /**
     * Test of getStartDate method, of class Ride.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        Ride instance = r1;
        Calendar expResult = cStart;
        Calendar result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStartDate method, of class Ride.
     */
    @Test
    public void testSetStartDate() {
        System.out.println("setStartDate");
        Calendar startDate = new GregorianCalendar(2006, 06, 06);
        Ride instance = r1;
        instance.setStartDate(startDate);
        assertEquals(startDate, r1.getStartDate());
    }

    /**
     * Test of getEndDate method, of class Ride.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        Ride instance = r1;
        Calendar expResult = cEnd;
        Calendar result = instance.getEndDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndDate method, of class Ride.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        Calendar endDate = new GregorianCalendar(2006, 06, 06);
        Ride instance = r1;
        instance.setEndDate(endDate);
        assertEquals(endDate, r1.getEndDate());
    }

    /**
     * Test of getLocations method, of class Ride.
     */
    @Test
    public void testGetLocations() {
        System.out.println("getLocations");
        Ride instance = r1;
        List<Location> expResult = new ArrayList<>();
        List<Location> result = instance.getLocations();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLocations method, of class Ride.
     */
    @Test
    public void testSetLocations() {
        System.out.println("setLocations");
        List<Location> locations = new ArrayList<Location>();
        locations.add(new Location(1L, cStart, 0.2, 0.3));
        Ride instance = r1;
        instance.setLocations(locations);
        assertEquals(locations, instance.getLocations());
    }
    
}
