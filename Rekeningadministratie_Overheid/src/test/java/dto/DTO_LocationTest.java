/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.Location;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class DTO_LocationTest {
    
    public DTO_LocationTest() {
    }
    
    private DTO_Location dtoLocation;
    private Location l;
    private long id;
    private Calendar date;
    private double latitude;
    private double longitude;
    
    @Before
    public void setUp() {
        id = 1L;
        date = new GregorianCalendar(2018, 01, 01);
        latitude = 1.0;
        longitude = 2.0;
        l = new Location(1L, date, latitude, longitude);
        
        dtoLocation = new DTO_Location(l.getDate().toString(), l.getId(), l.getLatitude(), l.getLongitude());
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDate method, of class DTO_Location.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        DTO_Location instance = dtoLocation;
        String expResult = date.toString();
        String result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class DTO_Location.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Calendar date = new GregorianCalendar(2000, 01, 01);
        DTO_Location instance = dtoLocation;
        instance.setDate(date.toString());
        String result = instance.getDate();
        assertEquals(date.toString(), result);
    }

    /**
     * Test of getId method, of class DTO_Location.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DTO_Location instance = dtoLocation;
        dtoLocation.setId(9L);
        long expResult = 9L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class DTO_Location.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 1L;
        DTO_Location instance = dtoLocation;
        instance.setId(id);
        long result = instance.getId();
        assertEquals(id, result);
    }

    /**
     * Test of getLatitude method, of class DTO_Location.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        DTO_Location instance = dtoLocation;
        double expResult = latitude;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLatitude method, of class DTO_Location.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 0.0;
        DTO_Location instance = dtoLocation;
        instance.setLatitude(latitude);
        double result = instance.getLatitude();
        assertEquals(latitude, result, 0.0);
    }

    /**
     * Test of getLongitude method, of class DTO_Location.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        DTO_Location instance = dtoLocation;
        double expResult = longitude;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLongitude method, of class DTO_Location.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = 0.0;
        DTO_Location instance = dtoLocation;
        instance.setLongitude(longitude);
        double result = instance.getLongitude();
        assertEquals(longitude, result, 0.0);
    }
    
}
