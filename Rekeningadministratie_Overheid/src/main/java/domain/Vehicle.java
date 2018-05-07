/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import enums.VehicleType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laurent
 */
public class Vehicle {
    private long id;
    private String autorisationNumber;
    private String serialNumber;
    
    private List<Rekeningrijder> ownersHistory;
    private Cartracker cartracker;
    private VehicleType vehicleType;

    public Vehicle(String autorisationNumber, String serialNumber, VehicleType vehicleType) {
        this.autorisationNumber = autorisationNumber;
        this.serialNumber = serialNumber;
        this.ownersHistory = new ArrayList<>();
        this.vehicleType = vehicleType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAutorisationNumber() {
        return autorisationNumber;
    }

    public void setAutorisationNumber(String autorisationNumber) {
        this.autorisationNumber = autorisationNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<Rekeningrijder> getOwnersHistory() {
        return ownersHistory;
    }

    public void setOwnersHistory(List<Rekeningrijder> ownersHistory) {
        this.ownersHistory = ownersHistory;
    }

    public Cartracker getCartracker() {
        return cartracker;
    }

    public void setCartracker(Cartracker cartracker) {
        this.cartracker = cartracker;
    }
    
    
    
}
