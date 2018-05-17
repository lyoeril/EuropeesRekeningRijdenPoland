/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dao.implementations;

import com.poland.entities.Location;
import com.poland.dao.interfaces.jpa.LocationDAO;
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
public class LocationDAOImpl extends BasicDAOImpl<Location> implements LocationDAO {

    @Override
    public List<Location> findLocationsByRideId(long id) {
        List<Location> locations = null;
        try {
            Query q = em.createQuery("select l from Location l where l.id = (select r.locations from Ride r where r.id = :id");
            q.setParameter("id", id);
            locations = (List<Location>) q.getResultList();
        } catch (Exception ise) {
            handleExceptions(ise);
        }
        return locations;
    }

}
