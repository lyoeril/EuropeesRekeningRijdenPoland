/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dto.entities;

import com.poland.entities.Ride;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PC-YOERI
 */
public class RideDTO {

    private long id;
    private Date startDate;
    private Date endDate;

    private List<LocationDTO> locations;

    public void fromRide(Ride ride) {
        id = ride.getId();
        startDate = ride.getStartDate();
        endDate = ride.getEndDate();

        locations = new ArrayList<>();
        
        DTOConverter.toLocationDTOList(ride.getLocations(), locations);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDTO> locations) {
        this.locations = locations;
    }
    
    
}
