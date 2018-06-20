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
public class BasicLocationDTOTest {

    public BasicLocationDTOTest() {
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
     * Test of fromLocation method, of class BasicLocationDTO.
     */
    @Test
    public void testFromLocation() {
        System.out.println("fromLocation");
        Location location = new Location(new Date(), 0, 0, new Ride());
        BasicLocationDTO instance = new BasicLocationDTO();
        instance.fromLocation(location);
    }

    /**
     * Test of getLatitude method, of class BasicLocationDTO.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        BasicLocationDTO instance = new BasicLocationDTO();
        double expResult = 0.0;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLatitude method, of class BasicLocationDTO.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 0.0;
        BasicLocationDTO instance = new BasicLocationDTO();
        instance.setLatitude(latitude);
    }

    /**
     * Test of getLongitude method, of class BasicLocationDTO.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        BasicLocationDTO instance = new BasicLocationDTO();
        double expResult = 0.0;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLongitude method, of class BasicLocationDTO.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = 0.0;
        BasicLocationDTO instance = new BasicLocationDTO();
        instance.setLongitude(longitude);
    }

}
