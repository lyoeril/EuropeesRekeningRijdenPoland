/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dao.interfaces.jpa;

import com.poland.entities.Ride;
import com.poland.entities.Vehicle;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PC-YOERI
 */
public interface VehicleDAO extends BasicDAO<Vehicle> {

    List<Ride> getAllRidesByVehicleId(long id);

    List<Ride> getAllFinishedRidesByVehicleId(long id);

    List<Ride> getRidesByDate(Date startDate, Date endDate, long id);

    Vehicle getVehicleByAuthorisationCode(String authorisationCode);

    List<Vehicle> getVehiclesIfStolen();
}
