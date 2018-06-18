/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dto.entities;

import com.poland.entities.Vehicle;
import java.util.ArrayList;
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
public class VehicleDTOTest {

    public VehicleDTOTest() {
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
     * Test of fromVehicle method, of class VehicleDTO.
     */
    @Test
    public void testFromVehicle() {
        System.out.println("fromVehicle");
        Vehicle vehicle = new Vehicle("PL-asta3234");
        vehicle.setId(1l);
        VehicleDTO instance = new VehicleDTO();
        instance.fromVehicle(vehicle);
    }

    /**
     * Test of getId method, of class VehicleDTO.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        VehicleDTO instance = new VehicleDTO();
        long expResult = 6L;
        instance.setId(expResult);
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class VehicleDTO.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 5L;
        VehicleDTO instance = new VehicleDTO();
        instance.setId(id);
    }

    /**
     * Test of getAuthorisationCode method, of class VehicleDTO.
     */
    @Test
    public void testGetAuthorisationCode() {
        System.out.println("getAuthorisationCode");
        VehicleDTO instance = new VehicleDTO();
        String expResult = "PL-test123";
        instance.setAuthorisationCode(expResult);
        String result = instance.getAuthorisationCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAuthorisationCode method, of class VehicleDTO.
     */
    @Test
    public void testSetAuthorisationCode() {
        System.out.println("setAuthorisationCode");
        String authorisationCode = "PL-test123";
        VehicleDTO instance = new VehicleDTO();
        instance.setAuthorisationCode(authorisationCode);
    }

    /**
     * Test of getRides method, of class VehicleDTO.
     */
    @Test
    public void testGetRides() {
        System.out.println("getRides");
        VehicleDTO instance = new VehicleDTO();
        List<RideDTO> expResult = new ArrayList<>();
        instance.setRides(expResult);
        List<RideDTO> result = instance.getRides();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRides method, of class VehicleDTO.
     */
    @Test
    public void testSetRides() {
        System.out.println("setRides");
        List<RideDTO> rides = new ArrayList<>();
        VehicleDTO instance = new VehicleDTO();
        instance.setRides(rides);
    }
}
