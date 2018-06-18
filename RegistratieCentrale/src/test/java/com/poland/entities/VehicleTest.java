/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.entities;

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
public class VehicleTest {

    public VehicleTest() {
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
     * Test of set and getId method, of class Vehicle.
     */
    @Test
    public void testSetGetId() {
        System.out.println("getId");
        Vehicle instance = new Vehicle("test", false, false);
        long expResult = 7L;
        instance.setId(expResult);
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of set and getAuthorisationCode method, of class Vehicle.
     */
    @Test
    public void testSetGetAuthorisationCode() {
        System.out.println("getAuthorisationCode");
        Vehicle instance = new Vehicle();
        String expResult = "test";
        instance.setAuthorisationCode(expResult);
        String result = instance.getAuthorisationCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of set and isStolen method, of class Vehicle.
     */
    @Test
    public void testSetIsStolen() {
        System.out.println("isStolen");
        Vehicle instance = new Vehicle();
        boolean expResult = false;
        boolean result = instance.isStolen();
        assertEquals(expResult, result);
        expResult = true;
        instance.setStolen(expResult);
        result = instance.isStolen();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeStolen method, of class Vehicle.
     */
    @Test
    public void testChangeStolen() {
        System.out.println("changeStolen");
        Vehicle instance = new Vehicle();
        assertEquals(false, instance.isStolen());
        instance.changeStolen();
        assertEquals(true, instance.isStolen());
        instance.changeStolen();
        assertEquals(false, instance.isStolen());
    }

    /**
     * Test of set and isForeignCar method, of class Vehicle.
     */
    @Test
    public void testSetIsForeignCar() {
        System.out.println("isForeignCar");
        Vehicle instance = new Vehicle();
        assertEquals(false, instance.isForeignCar());
        instance.setForeignCar(true);
        assertEquals(true, instance.isForeignCar());
    }

    /**
     * Test of set and getLocation method, of class Vehicle.
     */
    @Test
    public void testSetGetLocation() {
        System.out.println("getLocation");
        Vehicle instance = new Vehicle();
        Location expResult = new Location();
        instance.setLocation(expResult);
        Location result = instance.getLocation();
        assertEquals(expResult, result);

    }

    /**
     * Test of set and getRides method, of class Vehicle.
     */
    @Test
    public void testSetGetRides() {
        System.out.println("getRides");
        Vehicle instance = new Vehicle();
        List<Ride> expResult = new ArrayList<>();
        instance.setRides(expResult);
        List<Ride> result = instance.getRides();
        assertEquals(expResult, result);
    }

    /**
     * Test of addRide method, of class Vehicle.
     */
    @Test
    public void testAddRide() {
        System.out.println("addRide");
        Ride ride = new Ride();
        Vehicle instance = new Vehicle();
        instance.addRide(ride);
        assertEquals(instance.getRides().size(), 1);
    }

    /**
     * Test of removeRide method, of class Vehicle.
     */
    @Test
    public void testRemoveRide() {
        System.out.println("removeRide");
        Ride ride = new Ride();
        Vehicle instance = new Vehicle();
        instance.addRide(ride);
        assertEquals(instance.getRides().size(), 1);
        instance.removeRide(ride);
        assertEquals(instance.getRides().size(), 0);

    }

}
