/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.color.ColorSpace;
import java.sql.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class CartrackerTest {
    
    Cartracker c1;
    Cartracker c2;
    Cartracker c3;
    Cartracker c4;
    
    public CartrackerTest() {
    }
    
    @Before
    public void setUp() {
        c1 = new Cartracker("hardware1");
        c2 = new Cartracker("hardware2");
        c3 = new Cartracker("hardware3");
        c4 = new Cartracker("hardware4");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Cartracker.
     */
    @Test
    public void testGetId() {
        Cartracker instance = c1;
        instance.setId(1L);
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Cartracker.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 0L;
        Cartracker instance = c1;
        instance.setId(id);
        assertEquals(instance.getId(), id);
        
    }

    /**
     * Test of getHardware method, of class Cartracker.
     */
    @Test
    public void testGetHardware() {
        System.out.println("getHardware");
        Cartracker instance = c1;
        String expResult = "hardware1";
        String result = instance.getHardware();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHardware method, of class Cartracker.
     */
    @Test
    public void testSetHardware() {
        System.out.println("setHardware");
        String hardware = "newHardware";
        Cartracker instance = c1;
        instance.setHardware(hardware);
        assertEquals(instance.getHardware(), hardware);
    }

    /**
     * Test of getRides method, of class Cartracker.
     */
    @Test
    public void testGetRides() {
        System.out.println("getRides");
        Cartracker instance = c1;
        List<Ride> expResult = new ArrayList<Ride>();
        expResult.add(new Ride(1L, new GregorianCalendar(1, 1, 1990), new GregorianCalendar(2, 1, 1990)));
        instance.setRides(expResult);
        
        List<Ride> result = instance.getRides();
        assertEquals(expResult, result);
        assertEquals(1, instance.getRides().size());
    }

    /**
     * Test of setRides method, of class Cartracker.
     */
    @Test
    public void testSetRides() {
        System.out.println("getRides");
        Cartracker instance = c1;
        List<Ride> expResult = new ArrayList<Ride>();
        expResult.add(new Ride(1L, new GregorianCalendar(1, 1, 1990), new GregorianCalendar(2, 1, 1990)));
        instance.setRides(expResult);
        
        List<Ride> result = instance.getRides();
        assertEquals(expResult, result);
        assertEquals(1, instance.getRides().size());
    }
    
}
