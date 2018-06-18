/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author PC-YOERI
 */
@Entity
@Table(name = "t_vehicle")
public class Vehicle implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "authorisationCode", unique = true)
    private String authorisationCode;

    @Column(name = "stolen", nullable = false)
    private boolean stolen;

    @Column(name = "foreignCar", nullable = false)
    private boolean foreignCar;

    @JoinColumn(name = "locationId")
    private Location lastLocation;

    @Transient
    private List<Ride> rides;

    public Vehicle() {
        rides = new ArrayList<>();
    }

    public Vehicle(String authorisationCode) {
        rides = new ArrayList<>();
        setAuthorisationCode(authorisationCode);
        setStolen(false);
        setForeignCar(false);
    }

    public Vehicle(String authorisationCode, boolean stolen, boolean foreignCar) {
        rides = new ArrayList<>();
        setAuthorisationCode(authorisationCode);
        setStolen(stolen);
        setForeignCar(foreignCar);
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

    public boolean isStolen() {
        return stolen;
    }

    public void setStolen(boolean stolen) {
        this.stolen = stolen;
    }

    public void changeStolen() {
        if (this.stolen) {
            this.stolen = false;
        } else {
            this.stolen = true;
        }
    }

    public boolean isForeignCar() {
        return foreignCar;
    }

    public void setForeignCar(boolean foreignCar) {
        this.foreignCar = foreignCar;
    }

    public Location getLocation() {
        return lastLocation;
    }

    public void setLocation(Location location) {
        this.lastLocation = location;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public void addRide(Ride ride) {
        if (ride != null && !rides.contains(ride)) {
            rides.add(ride);
        }
    }

    public void removeRide(Ride ride) {
        if (ride != null && rides.contains(ride)) {
            rides.remove(ride);
        }
    }
}
