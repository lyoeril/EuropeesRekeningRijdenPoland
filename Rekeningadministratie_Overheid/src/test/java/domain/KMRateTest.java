/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import enums.VehicleType;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class KMRateTest {
    
    KMRate km1;
    KMRate km2;
    KMRate km3;
    
    public KMRateTest() {
    }
    
    @Before
    public void setUp() {
        km1 = new KMRate("region1");
        km2 = new KMRate("region2");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setId method, of class KMRate.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 99L;
        KMRate instance = km1;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getRegion method, of class KMRate.
     */
    @Test
    public void testGetRegion() {
        System.out.println("getRegion");
        KMRate instance = km1;
        String expResult = "region1";
        String result = instance.getRegion();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRegion method, of class KMRate.
     */
    @Test
    public void testSetRegion() {
        System.out.println("setRegion");
        String region = "region99";
        KMRate instance = km1;
        instance.setRegion(region);
        assertEquals(region, instance.getRegion());
    }

    /**
     * Test of getRatePerVehicleType method, of class KMRate.
     */
    @Test
    public void testGetRatePerVehicleType() {
        System.out.println("getRatePerVehicleType");
        KMRate instance = km1;
        Map<VehicleType, Double> expResult = new HashMap<VehicleType, Double>();
        Map<VehicleType, Double> result = instance.getRatePerVehicleType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRatePerVehicleType method, of class KMRate.
     */
    @Test
    public void testSetRatePerVehicleType() {
        System.out.println("setRatePerVehicleType");
        Map<VehicleType, Double> ratePerVehicleType = new HashMap<>();
        ratePerVehicleType.put(VehicleType.VAN, 0.2);
        KMRate instance = km1;
        instance.setRatePerVehicleType(ratePerVehicleType);
        assertEquals(ratePerVehicleType, instance.getRatePerVehicleType());
    }

    /**
     * Test of addRatePerVehicleType method, of class KMRate.
     */
    @Test
    public void testAddRatePerVehicleType() {
        System.out.println("addRatePerVehicleType");
        VehicleType vehicleType = VehicleType.AUTOBUS;
        double rate = 99.99;
        KMRate instance = km1;
        instance.addRatePerVehicleType(vehicleType, rate);
        assertEquals(rate, instance.getRateFromVehicleType(vehicleType), 0.0);
    }

    /**
     * Test of getRateFromVehicleType method, of class KMRate.
     */
    @Test
    public void testGetRateFromVehicleType() {
        System.out.println("getRateFromVehicleType");
        VehicleType vehicleType = VehicleType.PASSENGER_CAR;
        KMRate instance = km1;
        km1.addRatePerVehicleType(vehicleType, 22.0);
        double expResult = 22.0;
        double result = instance.getRateFromVehicleType(vehicleType);
        assertEquals(expResult, result, 0.0); 
    }
    
}
