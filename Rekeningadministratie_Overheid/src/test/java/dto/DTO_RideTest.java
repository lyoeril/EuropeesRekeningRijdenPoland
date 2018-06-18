/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

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

/**
 *
 * @author Laurent
 */
public class DTO_RideTest {

    public DTO_RideTest() {
    }

    Location l;
    Calendar date;
    Calendar starDate;
    Calendar endDate;
    Ride r;
    DTO_Ride dtoRide;
    DTO_Location dtoLocation;

    @Before
    public void setUp() {

        l = new Location(1L, date, 1.0, 2.0);
        
        date = new GregorianCalendar(2018, 01, 01);
        starDate = new GregorianCalendar(2018, 01, 01);
        endDate = new GregorianCalendar(2018, 02, 01);
        
        List<DTO_Location> locations = new ArrayList<>();
        dtoLocation = new DTO_Location(date.toString(), l.getId(), l.getLatitude(), l.getLongitude());
        locations.add(dtoLocation);
        r = new Ride(1L, starDate, endDate);

        dtoRide = new DTO_Ride(r.getId(), r.getStartDate().toString(), r.getEndDate().toString(), locations);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class DTO_Ride.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DTO_Ride instance = dtoRide;
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class DTO_Ride.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 0L;
        DTO_Ride instance = dtoRide;
        instance.setId(id);
        long result = instance.getId();
        assertEquals(id, result);
    }

    /**
     * Test of getStartDate method, of class DTO_Ride.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        DTO_Ride instance = dtoRide;
        String expResult = starDate.toString();
        String result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStartDate method, of class DTO_Ride.
     */
    @Test
    public void testSetStartDate() {
        System.out.println("setStartDate");
        Calendar newStart = new GregorianCalendar(2000, 01, 01);
        String startDate = newStart.toString();
        DTO_Ride instance = dtoRide;
        instance.setStartDate(startDate);
        
        String result = instance.getStartDate();
        assertEquals(newStart, newStart);
    }

    /**
     * Test of getEndDate method, of class DTO_Ride.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        DTO_Ride instance = dtoRide;
        String expResult = endDate.toString();
        String result = instance.getEndDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndDate method, of class DTO_Ride.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        Calendar newEnd = new GregorianCalendar(2000, 01, 01);
        String endDate = newEnd.toString();
        DTO_Ride instance = dtoRide;
        instance.setEndDate(endDate);
        
        String result = endDate.toString();
        assertEquals(newEnd, newEnd);
    }

    /**
     * Test of getLocations method, of class DTO_Ride.
     */
    @Test
    public void testGetLocations() {
        System.out.println("getLocations");
        DTO_Ride instance = dtoRide;
        int locations = instance.getLocations().size();
        assertEquals(1, locations);
    }

    /**
     * Test of setLocations method, of class DTO_Ride.
     */
    @Test
    public void testSetLocations() {
        System.out.println("setLocations");
        List<DTO_Location> locations = new ArrayList<>();
        locations.add(new DTO_Location(new GregorianCalendar().toString(), 2L, 0.1, 0.2));
        DTO_Ride instance = dtoRide;
        instance.setLocations(locations);
        List<DTO_Location> result = instance.getLocations();
        assertEquals(1, locations.size());
    }

}
