/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import domain.KMRate;
import enums.VehicleType;

/**
 *
 * @author Laurent
 */
public interface IKMRateDAO {
    void create(KMRate kmrate);
    void edit(KMRate kmrate);
    void delete(KMRate kmrate);
    KMRate findById(long id);
    KMRate findByRegion(String region);
    KMRate findByRegionVehicleType(String region, VehicleType vehicleType);
}
