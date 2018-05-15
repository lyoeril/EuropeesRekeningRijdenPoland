/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;

/**
 *
 * @author Laurent
 */
public class Location {

    private long id;
    private Calendar date;
    private double latitude;
    private double longitude;

    public Location() {

    }

    public Location(long id, Calendar date, double latitude, double longitude) {
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        if (latitude > 90L) {
            this.latitude = 90L;
        } else if (latitude < -90L) {
            this.latitude = -90L;
        } else {
            this.latitude = latitude;
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        if (longitude > 180L) {
            this.longitude = 180;
        } else if (longitude < -180L) {
            this.longitude = -180L;
        } else {
            this.longitude = longitude;
        }

    }
}
