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
public class PoliceVehicleDTOTest {

    public PoliceVehicleDTOTest() {
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
     * Test of fromVehicleLocation method, of class PoliceVehicleDTO.
     */
    @Test
    public void testFromVehicleLocation() {
        System.out.println("fromVehicleLocation");
        String uuid = "PL-taa93ars";
        Location location = new Location(new Date(), 0, 0, new Ride());
        PoliceVehicleDTO instance = new PoliceVehicleDTO();
        instance.fromVehicleLocation(uuid, location);
    }

    /**
     * Test of getUuid method, of class PoliceVehicleDTO.
     */
    @Test
    public void testGetUuid() {
        System.out.println("getUuid");
        PoliceVehicleDTO instance = new PoliceVehicleDTO();
        String expResult = "PL-taa93ars";
        instance.setUuid(expResult);
        String result = instance.getUuid();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUuid method, of class PoliceVehicleDTO.
     */
    @Test
    public void testSetUuid() {
        System.out.println("setUuid");
        String uuid = "PL-taa93ars";
        PoliceVehicleDTO instance = new PoliceVehicleDTO();
        instance.setUuid(uuid);
    }

    /**
     * Test of getCurrentLocation method, of class PoliceVehicleDTO.
     */
    @Test
    public void testGetCurrentLocation() {
        System.out.println("getCurrentLocation");
        PoliceVehicleDTO instance = new PoliceVehicleDTO();
        BasicLocationDTO expResult = new BasicLocationDTO();
        instance.setCurrentLocation(expResult);
        BasicLocationDTO result = instance.getCurrentLocation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurrentLocation method, of class PoliceVehicleDTO.
     */
    @Test
    public void testSetCurrentLocation() {
        System.out.println("setCurrentLocation");
        BasicLocationDTO currentLocation = null;
        PoliceVehicleDTO instance = new PoliceVehicleDTO();
        instance.setCurrentLocation(currentLocation);
    }

}
