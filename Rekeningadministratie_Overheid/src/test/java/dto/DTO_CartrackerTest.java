/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.Cartracker;
import domain.Location;
import domain.Ride;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sun.util.calendar.Gregorian;

/**
 *
 * @author Laurent
 */
public class DTO_CartrackerTest {
    
    public DTO_CartrackerTest() {
    }
    
    private DTO_Cartracker dtoCartracker;
    
    private Calendar startDate;
    private Calendar endDate;
    
    private Cartracker cartracker;
    private Location l1 = new Location();
    private Location l2 = new Location();
    private Ride ride;
    private List<Ride> rides;
    
    
    @Before
    public void setUp() {
        
        startDate = new GregorianCalendar(2018, 01, 01);
        endDate = new GregorianCalendar(2018, 02, 01);        
        ride = new Ride(1L, startDate, endDate);
        
        rides = new ArrayList<>();
        rides.add(ride);
        
        cartracker = new Cartracker("hardware");
        cartracker.setId(1L);
        cartracker.setRides(rides);
        
        dtoCartracker = new DTO_Cartracker(cartracker);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class DTO_Cartracker.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DTO_Cartracker instance = dtoCartracker;
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class DTO_Cartracker.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 2L;
        DTO_Cartracker instance = dtoCartracker;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getHardware method, of class DTO_Cartracker.
     */
    @Test
    public void testGetHardware() {
        System.out.println("getHardware");
        DTO_Cartracker instance = dtoCartracker;
        String expResult = "hardware";
        String result = instance.getHardware();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHardware method, of class DTO_Cartracker.
     */
    @Test
    public void testSetHardware() {
        System.out.println("setHardware");
        String hardware = "newHardware";
        DTO_Cartracker instance = dtoCartracker;
        instance.setHardware(hardware);
        assertEquals(hardware, instance.getHardware());
    }
    
}
