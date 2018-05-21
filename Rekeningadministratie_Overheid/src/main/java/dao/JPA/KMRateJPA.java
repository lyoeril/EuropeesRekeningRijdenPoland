/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.JPA;

import dao.interfaces.IKMRateDAO;
import domain.KMRate;
import enums.VehicleType;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Laurent
 */
@Stateless
public class KMRateJPA implements IKMRateDAO{
    private static final Logger LOGGER = Logger.getLogger(KMRateJPA.class.getName());
    
    @PersistenceContext(unitName = "Rekeningadministratie_Overheid")
    private EntityManager em ;

    public KMRateJPA(){   
    }
    
    public KMRateJPA(EntityManager em){
        this.em = em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(KMRate kmrate) {
        em.persist(kmrate);
    }

    @Override
    public void edit(KMRate kmrate) {
        em.merge(kmrate);
    }

    @Override
    public void delete(KMRate kmrate) {
        em.remove(kmrate);
    }

    @Override
    public KMRate findById(long id) {
        return em.find(KMRate.class, id);
    }

    @Override
    public KMRate findByRegion(String region) {
        return (KMRate)em.createNamedQuery("KMRate.findByRegion").setParameter("region", region).getSingleResult();
    }

    @Override
    public KMRate findByRegionVehicleType(String region, VehicleType vehicleType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
