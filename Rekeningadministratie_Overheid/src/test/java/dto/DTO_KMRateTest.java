/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.KMRate;
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
public class DTO_KMRateTest {
    
    public DTO_KMRateTest() {
    }
    
    private DTO_KMRate dtoKMRate;
    
    private KMRate kmrate;
    private String region1;
    private String region2;
    
    Map<VehicleType, Double> map;
    
    @Before
    public void setUp() {
        region1 = "region1";
        region2 = "region2";
        
        kmrate = new KMRate(region1);
        kmrate.setId(1L);
        map = new HashMap<>();
        map.put(VehicleType.VAN, 1.1);
        kmrate.setRatePerVehicleType(map);
        
        dtoKMRate = new DTO_KMRate(kmrate);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class DTO_KMRate.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DTO_KMRate instance = dtoKMRate;
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class DTO_KMRate.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 2L;
        DTO_KMRate instance = dtoKMRate;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getRates method, of class DTO_KMRate.
     */
    @Test
    public void testGetRates() {
        System.out.println("getRates");
        DTO_KMRate instance = dtoKMRate;
        Map<VehicleType, Double> expResult = map;
        Map<VehicleType, Double> result = instance.getRates();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRates method, of class DTO_KMRate.
     */
    @Test
    public void testSetRates() {
        System.out.println("setRates");
        map.put(VehicleType.AUTOBUS, 2.2);
        Map<VehicleType, Double> rates = map;
        DTO_KMRate instance = dtoKMRate;
        instance.setRates(rates);
        assertEquals(rates, instance.getRates());
    }

    /**
     * Test of getRegion method, of class DTO_KMRate.
     */
    @Test
    public void testGetRegion() {
        System.out.println("getRegion");
        DTO_KMRate instance = dtoKMRate;
        String expResult = region1;
        String result = instance.getRegion();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRegion method, of class DTO_KMRate.
     */
    @Test
    public void testSetRegion() {
        System.out.println("setRegion");
        String region = region2;
        DTO_KMRate instance = dtoKMRate;
        instance.setRegion(region);
        assertEquals(region, instance.getRegion());
    }
    
}
