/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC-YOERI
 */
public class LocationTest {

    public LocationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setId and getId method, of class Location.
     */
    @Test
    public void testSetGetId() {
        System.out.println("getId");
        Location instance = new Location();
        long expResult = 5L;
        instance.setId(expResult);
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate and getDate method, of class Location.
     */
    @Test
    public void testSetGetDate() {
        System.out.println("getDate");
        Location instance = new Location();
        Date expResult = new Date();
        instance.setDate(expResult);
        Date result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLatitude and getLatitude method, of class Location.
     */
    @Test
    public void testSetGetLatitude() {
        System.out.println("getLatitude");
        Location instance = new Location();
        double expResult = 50.0;
        instance.setLatitude(expResult);
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);

        expResult = 91.0;
        instance.setLatitude(expResult);
        result = instance.getLatitude();
        assertEquals(90.0, result, 0.0);

        expResult = -91.0;
        instance.setLatitude(expResult);
        result = instance.getLatitude();
        assertEquals(-90.0, result, 0.0);
    }

    /**
     * Test of setLongitude and getLongitude method, of class Location.
     */
    @Test
    public void testSetGetLongitude() {
        System.out.println("getLongitude");
        Location instance = new Location();
        double expResult = 50.0;
        instance.setLongitude(expResult);
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);

        expResult = 181.0;
        instance.setLongitude(expResult);
        result = instance.getLongitude();
        assertEquals(180.0, result, 0.0);

        expResult = -181.0;
        instance.setLongitude(expResult);
        result = instance.getLongitude();
        assertEquals(-180.0, result, 0.0);
    }

    /**
     * Test of setRide and getRide method, of class Location.
     */
    @Test
    public void testSetGetRide() {
        System.out.println("getRide");
        Location instance = new Location();
        Ride expResult = new Ride(new Date(), new Vehicle("test"));
        instance.setRide(expResult);
        Ride result = instance.getRide();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Location.
     */
    @Test
    public void testCompareTo() throws ParseException {
        System.out.println("compareTo");
        
        Date date1 = DateFormat.getDateInstance(DateFormat.SHORT,Locale.getDefault()).parse("18/06/2018");
        Date date2 = DateFormat.getDateInstance(DateFormat.SHORT,Locale.getDefault()).parse("17/06/2018");
        Date date3 = DateFormat.getDateInstance(DateFormat.SHORT,Locale.getDefault()).parse("19/06/2018");
        
        Location instance1 = new Location(date1, 50.0, 45.0, new Ride());
        Location instance2 = new Location(date2, 50.0, 45.0, new Ride());
        Location instance3 = new Location(date3, 50.0, 45.0, new Ride());
        
        assertEquals(0, instance1.compareTo(instance1));
        assertEquals(1, instance1.compareTo(instance2));
        assertEquals(-1, instance1.compareTo(instance3));
    }

    /**
     * Test of equals method, of class Location.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Location instance = new Location();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        result = instance.equals(instance);
        assertEquals(true, result);
    }

    /**
     * Test of hashCode method, of class Location.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Location instance = new Location();
        int expResult = 102487;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

}
