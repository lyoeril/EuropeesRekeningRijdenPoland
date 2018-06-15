/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.service;

import com.poland.entities.Location;
import com.poland.entities.Ride;
import com.poland.entities.Vehicle;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PC-YOERI
 */
@Stateless
public class RegistrationService {

    public static final long HOUR = 3600 * 1000;
    private LocationService locationService;
    private RideService rideService;
    private VehicleService vehicleService;

    @Inject
    public RegistrationService(LocationService locationService, RideService rideService, VehicleService vehicleService) {
        this.locationService = locationService;
        this.rideService = rideService;
        this.vehicleService = vehicleService;
    }

    public RegistrationService() {
    }

    public LocationService getLocationService() {
        return locationService;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    public RideService getRideService() {
        return rideService;
    }

    public void setRideService(RideService rideService) {
        this.rideService = rideService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public boolean registerLocationSP(Date date, Double latitude, Double longitude, String authorisationCode) {
        return locationService.insertLocationStoreProcedure(date, latitude, longitude, authorisationCode);
    }

    public boolean registerLocation(Date date, Double latitude, Double longitude, String authorisationCode) {
//        long start_time = System.nanoTime();

//        Vehicle v = vehicleService.getVehicleByAuthorisationCode(authorisationCode);
//        Ride r = null;
//
//        if (v == null) {
//            v = vehicleService.createVehicle(new Vehicle(authorisationCode));
//        }
//        Boolean done = false;
//        for (Ride t : v.getRides()) {
//            Date endDate = new Date(t.getEndDate().getTime() + HOUR);
//            if (t.getStartDate().compareTo(date) <= 0 && endDate.compareTo(date) >= 0) {
//                r = t;
//                done = true;
//                break;
//            }
//        }
//        if (!done) {
//            r = rideService.editRide(new Ride(date, v));
//            v.addRide(r);
//            v = vehicleService.editVehicle(v);
//        }
//
//        r.addRide(new Location(date, latitude, longitude, r));
//        r.setEndDate(date);
//        rideService.editRide(r);
//        long end_time = System.nanoTime();
//        System.out.println((end_time - start_time) / 1e6);
        return true;
    }

    public boolean registerLocationSimple(Date date, Double latitude, Double longitude, String authorisationCode) {
        Vehicle v = vehicleService.getVehicleByAuthorisationCode(authorisationCode);
        Ride r = null;

        if (v == null) {
            v = vehicleService.createVehicle(new Vehicle(authorisationCode));
        }

        r = rideService.findOrCreateRideByAutorisationCode(date, v);

        locationService.createLocation(new Location(date, latitude, longitude, r));
        r.setEndDate(date);
        rideService.editRide(r);
        return true;
    }
}
