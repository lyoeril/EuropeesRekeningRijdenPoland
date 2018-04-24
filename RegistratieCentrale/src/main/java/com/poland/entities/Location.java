package com.poland.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author PC-YOERI
 */
@Entity
public class Location {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    public Location() {
    }

    public Location(Date date, double latitude, double longitude) {
        setDate(date);
        setLatitude(latitude);
        setLongitude(longitude);
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
