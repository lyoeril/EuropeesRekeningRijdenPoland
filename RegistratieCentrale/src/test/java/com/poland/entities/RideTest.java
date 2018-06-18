/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class RideTest {

    public RideTest() {
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
     * Test of set and getId method, of class Ride.
     */
    @Test
    public void testSetGetId() {
        System.out.println("getId");
        Ride instance = new Ride();
        long expResult = 5L;
        instance.setId(expResult);
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of set and getStartDate method, of class Ride.
     */
    @Test
    public void testSetGetStartDate() {
        System.out.println("getStartDate");
        Ride instance = new Ride();
        Date expResult = new Date();
        instance.setStartDate(expResult);
        Date result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of set and getEndDate method, of class Ride.
     */
    @Test
    public void testSetGetEndDate() {
        System.out.println("getEndDate");
        Ride instance = new Ride();
        Date expResult = new Date();
        instance.setEndDate(expResult);
        Date result = instance.getEndDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of set and isMissedMeasurement method, of class Ride.
     */
    @Test
    public void testIsMissedMeasurement() {
        System.out.println("isMissedMeasurement");
        Ride instance = new Ride();
        boolean expResult = false;
        instance.setMissedMeasurement(expResult);
        boolean result = instance.isMissedMeasurement();
        assertEquals(expResult, result);
    }

    /**
     * Test of set and getVehicle method, of class Ride.
     */
    @Test
    public void testSetGetVehicle() {
        System.out.println("getVehicle");
        Ride instance = new Ride();
        Vehicle expResult = new Vehicle();
        instance.setVehicle(expResult);
        Vehicle result = instance.getVehicle();
        assertEquals(expResult, result);
    }

    /**
     * Test of set and getLocations method, of class Ride.
     */
    @Test
    public void testSetGetLocations() {
        System.out.println("getLocations");
        Ride instance = new Ride();
        List<Location> expResult = new ArrayList<>();
        instance.setLocations(expResult);
        List<Location> result = instance.getLocations();
        assertEquals(expResult, result);
    }

    /**
     * Test of addLocation method, of class Ride.
     */
    @Test
    public void testAddLocation() {
        System.out.println("addLocation");
        Location location = new Location();
        Ride instance = new Ride();
        instance.addLocation(location);
        assertEquals(instance.getLocations().size(), 1);
    }

    /**
     * Test of removeLocation method, of class Ride.
     */
    @Test
    public void testRemoveLocation() {
        System.out.println("removeLocation");
        Location location = new Location();
        Ride instance = new Ride();
        instance.addLocation(location);
        assertEquals(instance.getLocations().size(), 1);
        instance.removeLocation(location);
        assertEquals(instance.getLocations().size(), 0);
    }

}
