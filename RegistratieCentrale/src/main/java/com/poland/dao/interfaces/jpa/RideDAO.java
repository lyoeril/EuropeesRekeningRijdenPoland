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
public interface RideDAO extends BasicDAO<Ride> {

    Ride findRideByAuthorisationCodeAndDate(Date startDate, Date endDate, Vehicle vehicle);

    List<Ride> getRideByAuthorisationCodeAndDate(String authorisationCode, Date startDate, Date endDate);

//    boolean addLocation(long rideid, long locationid);
//
//    boolean removeLocation(long rideid, long locationid);
}
