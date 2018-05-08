/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.service;

import com.poland.dao.interfaces.jpa.RideDAO;
import com.poland.entities.Location;
import com.poland.entities.Ride;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
}
