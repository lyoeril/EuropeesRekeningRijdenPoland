/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.JPA;

import dao.interfaces.IVehicleDAO;
import domain.Vehicle;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Laurent
 */

@Stateless
public class VehicleJPA implements IVehicleDAO{
    
    private static final Logger LOGGER = Logger.getLogger(CartrackerJPA.class.getName());
    
    @PersistenceContext(unitName = "Rekeningadministratie_Overheid")
    private EntityManager em ;

    public VehicleJPA(){   
    }
    
    public VehicleJPA(EntityManager em){
        this.em = em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Vehicle vehicle) {
        em.persist(vehicle);
    }

    @Override
    public void edit(Vehicle vehicle) {
        em.merge(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        em.remove(vehicle);
    }

    @Override
    public Vehicle findVehicleById(long id) {
        return em.find(Vehicle.class, id);
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        return em.createNamedQuery("Vehicle.findAll").getResultList();
    }
    
}
