/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dao.implementations;

import com.poland.entities.Location;
import com.poland.dao.interfaces.jpa.LocationDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author PC-YOERI
 */
@Stateless
@Default
public class LocationDAOImpl extends BasicDAOImpl<Location> implements LocationDAO {

    @Override
    public List<Location> findLocationsByRideId(long id) {
        List<Location> locations = null;
        try {
            Query q = em.createQuery("select l from Location l where l.ride = (select r from Ride r where r.id = :id) order by l.date desc");
            q.setParameter("id", id);
            locations = (List<Location>) q.getResultList();
        } catch (Exception ise) {
            handleExceptions(ise);
        }
        return locations;
    }

    @Override
    public Location findLastLocationByAuthenticationCode(String authenticationCode) {
        Location location = null;
        try {
            Query q = em.createQuery("select l from Location l where l.ride = (select r from Ride r where r.vehicle = (select v from Vehicle v where v.authorisationCode : auth)) order by l.date desc");
            q.setParameter("auth", authenticationCode);
            q.setMaxResults(1);
            location = (Location) q.getSingleResult();
        } catch (Exception ise) {
            handleExceptions(ise);
        }
        return location;
    }

    @Override
    public boolean insertLocationStoreProcedure(Date date, Double latitude, Double longitude, String authorisationCode) {
        try {
            StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("insertLocationSP");
            query.setParameter("datetime", date);
            query.setParameter("latitude", latitude);
            query.setParameter("longitude", longitude);
            query.setParameter("authorisationCode", authorisationCode);
            query.execute();
            return true;
        } catch (Exception ise) {
            handleExceptions(ise);
        }
        return false;
    }
}
