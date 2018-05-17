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
import javax.enterprise.inject.Default;
import javax.persistence.Query;

/**
 *
 * @author PC-YOERI
 */
@Stateless
@Default
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
        try {
            em.remove(id);
        } catch (IllegalStateException ise) {
            handleExceptions(ise);
        }
    }

    @Override
    public Vehicle getVehicleByAuthorisationCode(String authorisationCode) {
        Vehicle vehicle = null;
        try {
            Query q = em.createQuery("SELECT v FROM Vehicle v WHERE v.authorisationCode = :authorisationCode");
            q.setParameter("authorisationCode", authorisationCode);
            vehicle = (Vehicle) q.getSingleResult();
        } catch (Exception ise) {
            handleExceptions(ise);
        }
        return vehicle;
    }
}
