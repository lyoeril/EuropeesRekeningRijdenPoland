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

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ride> rides;

    public Vehicle() {
        rides = new ArrayList<>();
    }

    public Vehicle(String authorisationCode) {
        rides = new ArrayList<>();
        setAuthorisationCode(authorisationCode);
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

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public void addLocation(Ride ride) {
        if (ride != null && !rides.contains(ride)) {
            rides.add(ride);
        }
    }

    public void removeLocation(Ride ride) {
        if (ride != null && rides.contains(ride)) {
            rides.remove(ride);
        }
    }
}
