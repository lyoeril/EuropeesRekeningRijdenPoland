/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dto.entities;

import com.poland.entities.Vehicle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC-YOERI
 */
public class VehicleDTO {

    private long id;
    private String authorisationCode;

    private List<RideDTO> rides;

    public void fromVehicle(Vehicle vehicle) {
        id = vehicle.getId();
        authorisationCode = vehicle.getAuthorisationCode();

        rides = new ArrayList<>();

        DTOConverter.toRideDTOList(vehicle.getRides(), rides);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorisationCode() {
        return authorisationCode;
    }

    public void setAuthorisationCode(String authorisationCode) {
        this.authorisationCode = authorisationCode;
    }

    public List<RideDTO> getRides() {
        return rides;
    }

    public void setRides(List<RideDTO> rides) {
        this.rides = rides;
    }
}
