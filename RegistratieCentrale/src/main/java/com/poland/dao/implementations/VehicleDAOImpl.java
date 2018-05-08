/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dao.implementations;

import com.poland.entities.Vehicle;
import com.poland.dao.interfaces.jpa.VehicleDAO;
import com.poland.entities.Ride;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author PC-YOERI
 */
@Stateless
public class VehicleDAOImpl extends BasicDAOImpl<Vehicle> implements VehicleDAO {

    @Override
    public List<Ride> getAllRidesByVehicleId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ride> getAllFinishedRidesByVehicleId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ride> getRidesByDate(Date startDate, Date endDate, long id) {
        throw new UnsupportedOperationException("not supp yet");
    }

    @Override
    public void addRide(Ride ride) {
        try {
            em.persist(ride);
        } catch (IllegalStateException ise) {
            handleExceptions(ise);
        }
    }

    @Override
    public void removeRide(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}