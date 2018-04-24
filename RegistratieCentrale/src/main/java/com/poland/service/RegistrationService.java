/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.service;

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
    
}
