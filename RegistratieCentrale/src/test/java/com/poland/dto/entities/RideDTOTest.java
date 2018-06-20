/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dto.entities;

import com.poland.entities.Ride;
import com.poland.entities.Vehicle;
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
public class RideDTOTest {

    public RideDTOTest() {
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
     * Test of fromRide method, of class RideDTO.
     */
    @Test
    public void testFromRide() {
        System.out.println("fromRide");
        Ride ride = new Ride(new Date(), new Vehicle("test"));
        ride.setId(1l);
        RideDTO instance = new RideDTO();
        instance.fromRide(ride);
    }

    /**
     * Test of getId method, of class RideDTO.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        RideDTO instance = new RideDTO();
        long expResult = 5L;
        instance.setId(expResult);
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class RideDTO.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 8L;
        RideDTO instance = new RideDTO();
        instance.setId(id);
    }

    /**
     * Test of getStartDate method, of class RideDTO.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        RideDTO instance = new RideDTO();
        Date expResult = new Date();
        instance.setStartDate(expResult);
        Date result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStartDate method, of class RideDTO.
     */
    @Test
    public void testSetStartDate() {
        System.out.println("setStartDate");
        Date startDate = new Date();
        RideDTO instance = new RideDTO();
        instance.setStartDate(startDate);
    }

    /**
     * Test of getEndDate method, of class RideDTO.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        RideDTO instance = new RideDTO();
        Date expResult = new Date();
        instance.setEndDate(expResult);
        Date result = instance.getEndDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndDate method, of class RideDTO.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        Date endDate = new Date();
        RideDTO instance = new RideDTO();
        instance.setEndDate(endDate);
    }

    /**
     * Test of getLocations method, of class RideDTO.
     */
    @Test
    public void testGetLocations() {
        System.out.println("getLocations");
        RideDTO instance = new RideDTO();
        List<LocationDTO> expResult = new ArrayList<>();
        instance.setLocations(expResult);
        List<LocationDTO> result = instance.getLocations();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLocations method, of class RideDTO.
     */
    @Test
    public void testSetLocations() {
        System.out.println("setLocations");
        List<LocationDTO> locations = new ArrayList<>();
        RideDTO instance = new RideDTO();
        instance.setLocations(locations);
    }
}
