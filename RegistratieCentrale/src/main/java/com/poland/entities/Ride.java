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
public class Ride implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "serialNumber", unique = true, nullable = false)
    private String serialNumber;

    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;

    @ElementCollection
    private List<Location> locations;

    public Ride() {
        locations = new ArrayList<>();
    }

    public Ride(Date startDate, String serialNumber, Vehicle vehicle) {
        locations = new ArrayList<>();
        setStartDate(startDate);
        setSerialNumber(serialNumber);
        setVehicle(vehicle);

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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
