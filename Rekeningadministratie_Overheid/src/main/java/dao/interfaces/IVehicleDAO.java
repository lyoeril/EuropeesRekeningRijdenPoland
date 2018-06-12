/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import domain.Vehicle;
import java.util.List;

/**
 *
 * @author Laurent
 */
public interface IVehicleDAO {

    void create(Vehicle vehicle);
    void edit(Vehicle vehicle);
    void delete(Vehicle vehicle);
    Vehicle findVehicleById(long id);
    List<Vehicle> findAllVehicles();
    Vehicle findVehicleByLicenseplate(String licenseplate);
}
