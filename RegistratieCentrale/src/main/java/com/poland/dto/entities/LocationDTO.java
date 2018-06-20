/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dto.entities;

import com.poland.entities.Location;
import java.util.Date;

/**
 *
 * @author PC-YOERI
 */
public class LocationDTO {

    private long id;
    private Date date;
    private double latitude;
    private double longitude;

    public void fromLocation(Location location) {
        id = location.getId();
        date = location.getDate();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
