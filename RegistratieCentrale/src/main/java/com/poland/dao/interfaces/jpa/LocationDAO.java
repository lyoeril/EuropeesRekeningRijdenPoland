/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.dao.interfaces.jpa;

import com.poland.entities.Location;
import java.util.List;

/**
 *
 * @author PC-YOERI
 */
public interface LocationDAO extends BasicDAO<Location>{
    List<Location> findLocationsByRideId(long id);
}
