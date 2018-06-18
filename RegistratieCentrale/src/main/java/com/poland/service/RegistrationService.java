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

    public boolean registerLocationSimple(Date date, Double latitude, Double longitude, String authorisationCode) {
        Vehicle v = vehicleService.getVehicleByAuthorisationCode(authorisationCode);
        Ride r = null;

        if (v == null) {
            v = vehicleService.createVehicle(new Vehicle(authorisationCode));
        }

        r = rideService.findOrCreateRideByAutorisationCode(date, v);

        Location l = locationService.createLocation(new Location(date, latitude, longitude, r));
        r.setEndDate(date);
        rideService.editRide(r);


        if (v.getLocation() == null) {
            v.setLocation(locationService.createLocation(new Location(date, latitude, longitude, null)));
        } else {
            Location location = v.getLocation();
            location.setDate(date);
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            v.setLocation(location);
        }
        vehicleService.editVehicle(v);
        return true;
    }

    public void reportStolenVehicle(String uuid, Location location) {
        Vehicle v = vehicleService.getVehicleByAuthorisationCode(uuid);

        if (v == null && location != null) {
            Location l = locationService.createLocation(location);
            v = vehicleService.createVehicle(new Vehicle(uuid, true, true));
            v.setLocation(l);
            vehicleService.editVehicle(v);
        } else if (v != null && location != null && v.isForeignCar()) {
            v.changeStolen();
            Location l = v.getLocation();
            l.setLatitude(location.getLatitude());
            l.setLongitude(location.getLongitude());
            v.setLocation(l);
            vehicleService.editVehicle(v);
        } else if (v != null && !v.isForeignCar()) {
            v.changeStolen();
            vehicleService.editVehicle(v);
        }
    }
}
