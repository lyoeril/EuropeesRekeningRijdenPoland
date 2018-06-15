/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.service;

import com.poland.dao.interfaces.jpa.VehicleDAO;
import com.poland.entities.Ride;
import com.poland.entities.Vehicle;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PC-YOERI
 */
@Stateless
public class VehicleService {

    private VehicleDAO vehicleDAO;

    @Inject
    public VehicleService(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    public VehicleService() {
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        try {
            if (vehicleDAO.getVehicleByAuthorisationCode(vehicle.getAuthorisationCode()) == null) {
                return vehicleDAO.create(vehicle);
            }
        } catch (IllegalArgumentException ex) {
            return null;
        }
        return null;
    }

    public Vehicle editVehicle(Vehicle vehicle) {
        return vehicleDAO.edit(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicleDAO.remove(vehicle);
    }

//    public void addRide(Ride ride) {
//        Vehicle vehicle = vehicleDAO.find(ride.getVehicle().getId());
//        vehicle.getRides().add(ride);
//
//        vehicleDAO.edit(vehicle);
//    }

    public Vehicle getVehicleByAuthorisationCode(String authorisationCode) {
        return vehicleDAO.getVehicleByAuthorisationCode(authorisationCode);
    }

//    public void removeRide(long id) {
//        vehicleDAO.removeRide(id);
//    }

    public List<Ride> getAllRidesByVehicle(long id) {
        return vehicleDAO.getAllRidesByVehicleId(id);
    }

    public List<Ride> getRidesByDate(Date startDate, Date endDate, long id) {
        return vehicleDAO.getRidesByDate(startDate, endDate, id);
    }

    public List<Ride> getAllFinishedRidesByVehicle(long id) {
        return vehicleDAO.getAllFinishedRidesByVehicleId(id);
    }

}
