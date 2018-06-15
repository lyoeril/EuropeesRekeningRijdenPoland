/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.Vehicle;
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
public class DTO_VehicleTest {
    
    public DTO_VehicleTest() {
    }
    
    private Vehicle vehicle;
    private DTO_Vehicle dtoVehicle;
    
    private long id;
    private VehicleType vehicleType;
    private String licensePlate;
    private List<Long> ownersHistory;
    
    
    
    @Before
    public void setUp() {
        id = 1L;
        vehicleType = VehicleType.AUTOBUS;
        licensePlate = "licensePlate";        
        ownersHistory = new ArrayList<>();
        vehicle = new Vehicle(vehicleType, licensePlate);
        vehicle.setId(id);
        
        dtoVehicle = new DTO_Vehicle(vehicle);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class DTO_Vehicle.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DTO_Vehicle instance = dtoVehicle;
        long expResult = id;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class DTO_Vehicle.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 2L;
        DTO_Vehicle instance = dtoVehicle;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getLicensePlate method, of class DTO_Vehicle.
     */
    @Test
    public void testGetLicensePlate() {
        System.out.println("getLicensePlate");
        DTO_Vehicle instance = dtoVehicle;
        String expResult = licensePlate;
        String result = instance.getLicensePlate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLicensePlate method, of class DTO_Vehicle.
     */
    @Test
    public void testSetLicensePlate() {
        System.out.println("setLicensePlate");
        String licensePlate = "newLicensePlate";
        DTO_Vehicle instance = dtoVehicle;
        instance.setLicensePlate(licensePlate);
        assertEquals(licensePlate, dtoVehicle.getLicensePlate());
    }

    /**
     * Test of getVehicleType method, of class DTO_Vehicle.
     */
    @Test
    public void testGetVehicleType() {
        System.out.println("getVehicleType");
        DTO_Vehicle instance = dtoVehicle;
        VehicleType expResult = vehicleType;
        VehicleType result = instance.getVehicleType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVehicleType method, of class DTO_Vehicle.
     */
    @Test
    public void testSetVehicleType() {
        System.out.println("setVehicleType");
        VehicleType vehicleType = VehicleType.PASSENGER_CAR;
        DTO_Vehicle instance = dtoVehicle;
        instance.setVehicleType(vehicleType);
        assertEquals(vehicleType, instance.getVehicleType());
    }

    /**
     * Test of getOwnersHistory method, of class DTO_Vehicle.
     */
    @Test
    public void testGetOwnersHistory() {
        System.out.println("getOwnersHistory");
        DTO_Vehicle instance = dtoVehicle;
        List<Long> expResult = new ArrayList<>();
        List<Long> result = instance.getOwnersHistory();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOwnersHistory method, of class DTO_Vehicle.
     */
    @Test
    public void testSetOwnersHistory() {
        System.out.println("setOwnersHistory");
        List<Long> ownersHistory = this.ownersHistory;
        ownersHistory.add(1L);
        DTO_Vehicle instance = dtoVehicle;
        instance.setOwnersHistory(ownersHistory);
        assertEquals(ownersHistory, instance.getOwnersHistory());
    }

    /**
     * Test of getCartrackerId method, of class DTO_Vehicle.
     */
    @Test
    public void testGetCartrackerId() {
        System.out.println("getCartrackerId");
        DTO_Vehicle instance = dtoVehicle;
        long expResult = 0L;
        long result = instance.getCartrackerId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCartrackerId method, of class DTO_Vehicle.
     */
    @Test
    public void testSetCartrackerId() {
        System.out.println("setCartrackerId");
        long cartrackerId = 1L;
        DTO_Vehicle instance = dtoVehicle;
        instance.setCartrackerId(cartrackerId);
        assertEquals(cartrackerId, instance.getCartrackerId());
    }
    
}
