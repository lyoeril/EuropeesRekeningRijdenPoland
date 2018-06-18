/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.service;

import com.poland.dao.interfaces.jpa.LocationDAO;
import com.poland.dao.interfaces.jpa.RideDAO;
import com.poland.entities.Ride;
import com.poland.entities.Vehicle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PC-YOERI
 */
@Stateless
public class RideService {

    public static final long HOUR = 3600L * 1000L;
    private RideDAO rideDAO;
    private LocationDAO locationDAO;

    @Inject
    public RideService(RideDAO rideDAO, LocationDAO locationDAO) {
        this.rideDAO = rideDAO;
        this.locationDAO = locationDAO;
    }

    public RideService() {
    }

    public Ride findOrCreateRideByAutorisationCode(Date date, Vehicle vehicle) {
        if (vehicle != null) {
            Ride r = rideDAO.findRideByAuthorisationCodeAndDate(date, new Date(date.getTime() - HOUR), vehicle);
            if (r == null) {
                r = rideDAO.create(new Ride(date, vehicle));
            }
            return r;
        } else {
            return null;
        }
    }

//    public boolean addLocation(long rideId, long locationId) {
//        if (rideId != 0L && locationId > 0L) {
//            return rideDAO.addLocation(rideId, locationId);
//        }
//        return false;
//    }
//
//    public boolean removeLocation(long rideId, long locationId) {
//        if (rideId != 0L && locationId > 0L) {
//            return rideDAO.removeLocation(rideId, locationId);
//        }
//        return false;
//    }
    public Ride createRide(Ride ride) {
        try {
            if (rideDAO.find(ride.getId()) == null) {
                return rideDAO.create(ride);
            }
        } catch (IllegalArgumentException ex) {
            return null;
        }
        return null;
    }

    public Ride editRide(Ride ride) {
        return rideDAO.edit(ride);
    }

    public boolean deleteRide(long id) {
        try {
            Ride ride = rideDAO.find(id);

            ride.setVehicle(null);

            ride = rideDAO.edit(ride);
            rideDAO.remove(ride);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public List<Ride> getRidesOnDate(String authenticationCode, Date start, Date end) {
        if (authenticationCode != null && !authenticationCode.equals("") && start != null && end != null) {
            List<Ride> rides = rideDAO.getRideByAuthorisationCodeAndDate(authenticationCode, start, end);
            for (Ride ride : rides) {
                ride.setLocations(locationDAO.findLocationsByRideId(ride.getId()));
            }
            return rides;
        } else {
            return new ArrayList<>();
        }
    }
}
