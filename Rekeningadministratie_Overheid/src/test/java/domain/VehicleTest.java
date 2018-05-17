/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import enums.VehicleType;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class VehicleTest {
    
    Vehicle v1;
    Vehicle v2;
    
    public VehicleTest() {
    }
    
    @Before
    public void setUp() {
        v1 = new Vehicle(VehicleType.PASSENGER_CAR, "licensePlate");
        v1.setId(1L);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Vehicle.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Vehicle instance = v1;
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Vehicle.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 99L;
        Vehicle instance = v1;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getAutorisationNumber method, of class Vehicle.
     */
    @Test
    public void testGetLicensePlate() {
        System.out.println("getLicensePlate");
        Vehicle instance = v1;
        String expResult = "licensePlate";
        String result = instance.getLicensePlate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAutorisationNumber method, of class Vehicle.
     */
    @Test
    public void testSetAutorisationNumber() {
        System.out.println("setLicensePlate");
        String autorisationNumber = "aNumber99";
        Vehicle instance = v1;
        instance.setLicensePlate(autorisationNumber);
        assertEquals(autorisationNumber, instance.getLicensePlate());
    }

    /**
     * Test of getOwnersHistory method, of class Vehicle.
     */
    @Test
    public void testGetOwnersHistory() {
        System.out.println("getOwnersHistory");
        Vehicle instance = v1;
        List<Rekeningrijder> expResult = new ArrayList<Rekeningrijder>();
        List<Rekeningrijder> result = instance.getOwnersHistory();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOwnersHistory method, of class Vehicle.
     */
    @Test
    public void testSetOwnersHistory() {
        System.out.println("setOwnersHistory");
        List<Rekeningrijder> ownersHistory = new ArrayList<>();
        Rekeningrijder r1 = new Rekeningrijder("name1", "address1", "asdf", "asdf");
        ownersHistory.add(r1);
        Vehicle instance = v1;
        instance.setOwnersHistory(ownersHistory);
        assertTrue(instance.getOwnersHistory().contains(r1));
    }

    /**
     * Test of getCartracker method, of class Vehicle.
     */
    @Test
    public void testGetCartracker() {
        System.out.println("getCartracker");
        Vehicle instance = v1;
        Cartracker expResult = null;
        Cartracker result = instance.getCartracker();
        assertNull(result);
    }

    /**
     * Test of setCartracker method, of class Vehicle.
     */
    @Test
    public void testSetCartracker() {
        System.out.println("setCartracker");
        Cartracker cartracker = new Cartracker("hw");
        Vehicle instance = v1;
        instance.setCartracker(cartracker);
        assertEquals(cartracker, instance.getCartracker());
    }
    
}
