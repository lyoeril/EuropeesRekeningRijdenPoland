package com.poland.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author PC-YOERI
 */
@Entity
@Table(name = "t_location")
@NamedStoredProcedureQuery(
        name = "insertLocationSP",
        procedureName = "insertLocationSP",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "datetime")
            ,
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Double.class, name = "latitude")
            ,
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Double.class, name = "longitude")
            ,
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "authorisationCode")
            ,
            @StoredProcedureParameter(mode = ParameterMode.OUT, type = Long.class, name = "succeeded")
        }
)
public class Location implements Serializable, Comparable<Location> {

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

    @ManyToOne
    @JoinColumn(name = "rideId", nullable = false)
    private Ride ride;

    public Location() {
    }

    public Location(Date date, double latitude, double longitude, Ride ride) {
        setDate(date);
        setLatitude(latitude);
        setLongitude(longitude);
        setRide(ride);
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

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    @Override
    public int compareTo(Location o) {
        return getDate().compareTo(o.getDate());
    }
    
    
}
