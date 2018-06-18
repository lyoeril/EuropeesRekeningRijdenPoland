/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.service;

import com.poland.dao.interfaces.jpa.LocationDAO;
import com.poland.entities.Location;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PC-YOERI
 */
@Stateless
public class LocationService {

    private LocationDAO locationDAO;

    @Inject
    public LocationService(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public LocationService() {
    }

    public List<Location> getLocationsByRideId(long id) {
        return locationDAO.findLocationsByRideId(id);
    }

    public Location createLocation(Location location) {
        try {
            if (locationDAO.find(location.getId()) == null) {
                return locationDAO.create(location);
            }
        } catch (IllegalArgumentException ex) {
            return null;
        }
        return null;
    }

    public Location editLocation(Location location) {
        return locationDAO.edit(location);
    }

    public boolean deleteLocation(long id) {
        try {
            Location location = locationDAO.find(id);

            locationDAO.edit(location);
            locationDAO.remove(locationDAO.find(id));
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public Location getLocatieById(long id) {
        try {
            if (id != 0L && id > 0L) {
                return locationDAO.find(id);
            } else {
                return null;
            }
        } catch (NullPointerException x) {
            return null;
        }

    }

    public Location findLastLocationByAuthenticationCode(String authenticationCode) {
        try {
            if (authenticationCode != null && !authenticationCode.equals("")) {
                return locationDAO.findLastLocationByAuthenticationCode(authenticationCode);
            } else {
                return null;
            }
        } catch (NullPointerException x) {
            return null;
        }

    }

    public boolean insertLocationStoreProcedure(Date date, Double latitude, Double longitude, String authorisationCode) {
        if (date == null || latitude >= 90 || latitude <= -90 || longitude >= 180 || longitude <= -180 || authorisationCode == null || authorisationCode.equals("")) {
            return false;
        } else {
            return locationDAO.insertLocationStoreProcedure(date, latitude, longitude, authorisationCode);
        }
    }
}
