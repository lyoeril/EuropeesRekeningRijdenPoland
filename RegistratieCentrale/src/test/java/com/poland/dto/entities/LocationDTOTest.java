/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dto.entities;

import com.poland.entities.Location;
import com.poland.entities.Ride;
import java.util.Date;
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
public class LocationDTOTest {

    public LocationDTOTest() {
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
     * Test of fromLocation method, of class LocationDTO.
     */
    @Test
    public void testFromLocation() {
        System.out.println("fromLocation");
        Location location = new Location(new Date(), 0, 0, new Ride());
        location.setId(1l);
        LocationDTO instance = new LocationDTO();
        instance.fromLocation(location);
    }

    /**
     * Test of getId method, of class LocationDTO.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        LocationDTO instance = new LocationDTO();
        long expResult = 0L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class LocationDTO.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 0L;
        LocationDTO instance = new LocationDTO();
        instance.setId(id);
    }

    /**
     * Test of getDate method, of class LocationDTO.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        LocationDTO instance = new LocationDTO();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class LocationDTO.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = null;
        LocationDTO instance = new LocationDTO();
        instance.setDate(date);
    }

    /**
     * Test of getLatitude method, of class LocationDTO.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        LocationDTO instance = new LocationDTO();
        double expResult = 0.0;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLatitude method, of class LocationDTO.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 0.0;
        LocationDTO instance = new LocationDTO();
        instance.setLatitude(latitude);
    }

    /**
     * Test of getLongitude method, of class LocationDTO.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        LocationDTO instance = new LocationDTO();
        double expResult = 0.0;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLongitude method, of class LocationDTO.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = 0.0;
        LocationDTO instance = new LocationDTO();
        instance.setLongitude(longitude);
    }

}
