/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.service;

import com.poland.dao.interfaces.jpa.RideDAO;
import com.poland.entities.Ride;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PC-YOERI
 */
@Stateless
public class RideService {

    private RideDAO rideDAO;

    @Inject
    public RideService(RideDAO rideDAO) {
        this.rideDAO = rideDAO;
    }

    public RideService() {
    }

    public Ride findRideBySerialnumber(String serialNumber) {
        if (serialNumber != null || serialNumber.equals("")) {
            return rideDAO.findRideBySerialNumber(serialNumber);
        } else {
            return null;
        }
    }

    public boolean addLocation(long rideId, long locationId) {
        if (rideId != 0L && locationId > 0L) {
            return rideDAO.addLocation(rideId, locationId);
        }
        return false;
    }

    public boolean removeLocation(long rideId, long locationId) {
        if (rideId != 0L && locationId > 0L) {
            return rideDAO.removeLocation(rideId, locationId);
        }
        return false;
    }
    
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

            ride.getLocations().forEach((t) -> {
                ride.removeLocation(t);
            });
            rideDAO.edit(ride);
            rideDAO.remove(rideDAO.find(id));
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
