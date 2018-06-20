/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dto.entities;

import com.poland.entities.Location;
import com.poland.entities.Ride;
import com.poland.entities.Vehicle;
import java.util.ArrayList;
import java.util.Date;
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
public class DTOConverterTest {

    public DTOConverterTest() {
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
     * Test of toRideDTOList method, of class DTOConverter.
     */
    @Test
    public void testToRideDTOList() {
        System.out.println("toRideDTOList");
        List<Ride> rideList = new ArrayList<>();
        Ride r = new Ride(new Date(), new Vehicle("PL-test"));
        r.setId(1L);
        Ride r2 = new Ride(new Date(), new Vehicle("PL-test2"));
        r.setId(2L);
        rideList.add(r);
        rideList.add(r2);

        List<RideDTO> rideTargetList = new ArrayList<>();
        DTOConverter.toRideDTOList(rideList, rideTargetList);
    }

    /**
     * Test of toLocationDTOList method, of class DTOConverter.
     */
    @Test
    public void testToLocationDTOList() {
        System.out.println("toLocationDTOList");
        List<Location> locationList = new ArrayList<>();
        Location l1 = new Location(new Date(), 12, 51, new Ride(new Date(), new Vehicle("PL-test2")));
        l1.setId(1L);
        Location l2 = new Location(new Date(), 12, 51, new Ride(new Date(), new Vehicle("PL-test2")));
        l2.setId(2L);
        locationList.add(l1);
        locationList.add(l2);

        List<LocationDTO> locationTargetList = new ArrayList<>();
        DTOConverter.toLocationDTOList(locationList, locationTargetList);
    }

    /**
     * Test of toBasicLocationDTOList method, of class DTOConverter.
     */
    @Test
    public void testToBasicLocationDTOList() {
        System.out.println("toBasicLocationDTOList");
        List<Location> locationList = new ArrayList<>();
        Location l1 = new Location(new Date(), 12, 51, new Ride(new Date(), new Vehicle("PL-test2")));
        l1.setId(1L);
        Location l2 = new Location(new Date(), 12, 51, new Ride(new Date(), new Vehicle("PL-test2")));
        l2.setId(2L);

        locationList.add(l1);
        locationList.add(l2);
        List<BasicLocationDTO> basiclocationTargetList = new ArrayList<>();
        DTOConverter.toBasicLocationDTOList(locationList, basiclocationTargetList);
    }

    /**
     * Test of toVehicleDTOList method, of class DTOConverter.
     */
    @Test
    public void testToVehicleDTOList() {
        System.out.println("toVehicleDTOList");
        List<Vehicle> vehicleList = new ArrayList<>();
        Vehicle v1 = new Vehicle("PL-Test1");
        v1.setId(1l);
        Vehicle v2 = new Vehicle("PL-Test2");
        v2.setId(2l);

        vehicleList.add(v1);
        vehicleList.add(v2);

        List<VehicleDTO> vehicleTargetList = new ArrayList<>();
        DTOConverter.toVehicleDTOList(vehicleList, vehicleTargetList);
    }

    /**
     * Test of toPoliceVehicleDTOList method, of class DTOConverter.
     */
    @Test
    public void testToPoliceVehicleDTOList() {
        System.out.println("toPoliceVehicleDTOList");
        List<Vehicle> vehicleList = new ArrayList<>();

        Vehicle v1 = new Vehicle("PL-Test1");
        v1.setId(1l);
        v1.setLocation(new Location(new Date(), 15, 27, new Ride()));
        
        Vehicle v2 = new Vehicle("PL-Test2");
        v2.setId(2l);
        v2.setLocation(new Location(new Date(), 25, 15, new Ride()));
        
        vehicleList.add(v1);
        vehicleList.add(v2);

        List<PoliceVehicleDTO> policeVehicleTargetList = new ArrayList<>();
        DTOConverter.toPoliceVehicleDTOList(vehicleList, policeVehicleTargetList);
    }

}
