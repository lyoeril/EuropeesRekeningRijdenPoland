/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.service;

import com.poland.entities.Location;
import com.poland.entities.Ride;
import com.poland.entities.Vehicle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PC-YOERI
 */
@Stateless
public class RegistrationService {

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

    public boolean registerLocation(Date date, Double latitude, Double longitude, String authorisationCode) {
        Vehicle vehicle = null;
        if (authorisationCode != null && authorisationCode != "") {
            vehicle = vehicleService.getVehicleByAuthorisationCode(authorisationCode);
        }
        System.out.println(vehicle);
        if (vehicle == null) {
            vehicle = vehicleService.createVehicle(new Vehicle(authorisationCode));
        }

        List<Ride> rides = new ArrayList<>();

        vehicle.getRides().forEach(r -> {
            if (r.getEndDate() == null) {
                rides.add(r);
            }
        });

        Ride ride = null;
        if (rides.isEmpty()) {
            String serialNumber = String.valueOf(System.currentTimeMillis());
            ride = rideService.createRide(new Ride(date, serialNumber, vehicle));
            ride = rideService.findRideBySerialnumber(serialNumber);
            vehicleService.addRide(ride);
        } else {
            ride = rides.get(0);

            List<Location> locations = ride.getLocations();
            Collections.sort(locations);

            Location lastlocation = locations.get(ride.getLocations().size() - 1);

            double diffInMillis = (double) Math.abs(date.getTime() - lastlocation.getDate().getTime());
            double diff = diffInMillis / 3600000;

            if (diff > 1) {
                ride.setEndDate(lastlocation.getDate());
                rideService.editRide(ride);

                String serialNumber = String.valueOf(System.currentTimeMillis());
                ride = rideService.createRide(new Ride(date, serialNumber, vehicle));
                vehicleService.addRide(ride);
                ride = rideService.findRideBySerialnumber(serialNumber);
            }
        }

        Location location = new Location(date, latitude, longitude, ride);
        location = locationService.createLocation(location);
        
        List<Location> rideLocations = locationService.getLocationsByRideId(ride.getId());
        Collections.sort(rideLocations);
        
        Location newlocation = rideLocations.get(ride.getLocations().size() - 1);
        
        rideService.addLocation(ride.getId(), newlocation.getId());
        return true;
    }

}
