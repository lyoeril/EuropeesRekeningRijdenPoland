/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dao.implementations;

import com.poland.entities.Ride;
import com.poland.dao.interfaces.jpa.RideDAO;
import com.poland.entities.Vehicle;
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
public class RideDAOImpl extends BasicDAOImpl<Ride> implements RideDAO {

    @Override
    public Ride findRideByAuthorisationCodeAndDate(Date startDate, Date endDate, Vehicle vehicle) {
        Ride ride = null;
        try {
            Query q = em.createQuery("select r from Ride r where r.vehicle = :vehicle and r.startDate <= :startDate and r.endDate >= :endDate");
            q.setParameter("vehicle", vehicle);
            q.setParameter("startDate", startDate);
            q.setParameter("endDate", endDate);
            ride = (Ride) q.getSingleResult();
        } catch (Exception ise) {
            handleExceptions(ise);
        }
        return ride;
    }

    @Override
    public List<Ride> getRideByAuthorisationCodeAndDate(String authorisationCode, Date startDate, Date endDate) {
        List<Ride> ride = null;
        try {
            Query q = em.createQuery("select r from Ride r where r.vehicle = (select v from Vehicle v where v.authorisationCode = :authorisationCode) and r.startDate >= :startDate and r.endDate is not null and r.endDate <= :endDate");
            q.setParameter("authorisationCode", authorisationCode);
            q.setParameter("startDate", startDate);
            q.setParameter("endDate", endDate);
            ride = (List<Ride>) q.getResultList();
        } catch (Exception ise) {
            handleExceptions(ise);
        }
        return ride;
    }
}
