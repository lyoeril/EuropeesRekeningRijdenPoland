/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dto.entities;

import com.poland.entities.Location;

/**
 *
 * @author PC-YOERI
 */
public class PoliceVehicleDTO {

    private String uuid;
    private BasicLocationDTO currentLocation;

    public void fromVehicleLocation(String uuid, Location location) {
        this.uuid = uuid;

        currentLocation = new BasicLocationDTO();
        currentLocation.fromLocation(location);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public BasicLocationDTO getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(BasicLocationDTO currentLocation) {
        this.currentLocation = currentLocation;
    }
    
    
}
