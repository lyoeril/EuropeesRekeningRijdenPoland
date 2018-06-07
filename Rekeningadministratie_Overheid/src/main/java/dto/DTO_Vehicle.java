/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.Rekeningrijder;
import enums.VehicleType;
import domain.Vehicle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laurent
 */
public class DTO_Vehicle {

    private long id;
    private String licensePlate;
    private VehicleType vehicleType;
    private List<Long> ownersHistory;

    public DTO_Vehicle(Vehicle v) {
        this.id = v.getId();
        this.licensePlate = v.getLicensePlate();
        this.vehicleType = v.getVehicleType();

        if (v.getOwnersHistory() != null) {
            ownersHistory = new ArrayList<>();
            for (Rekeningrijder r : v.getOwnersHistory()) {
                ownersHistory.add(r.getId());
            }
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<Long> getOwnersHistory() {
        return ownersHistory;
    }

    public void setOwnersHistory(List<Long> ownersHistory) {
        this.ownersHistory = ownersHistory;
    }

}
