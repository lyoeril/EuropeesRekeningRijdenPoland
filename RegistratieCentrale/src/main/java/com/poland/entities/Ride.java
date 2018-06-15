/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author PC-YOERI
 */
@Entity
@Table(name = "t_ride")
public class Ride implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "missedMeasurement")
    private boolean missedMeasurement;

    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;

    @Transient
    private List<Location> locations;

    public Ride() {
        locations = new ArrayList<>();
    }

    public Ride(Date startDate, Vehicle vehicle) {
        locations = new ArrayList<>();
        setStartDate(startDate);
        setEndDate(startDate);
        setVehicle(vehicle);
        missedMeasurement = false;
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

    public boolean isMissedMeasurement() {
        return missedMeasurement;
    }

    public void setMissedMeasurement(boolean missedMeasurement) {
        this.missedMeasurement = missedMeasurement;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        if (location != null && !locations.contains(location)) {
            locations.add(location);
        }
    }

    public void removeLocation(Location location) {
        if (location != null && locations.contains(location)) {
            locations.remove(location);
        }
    }
}
