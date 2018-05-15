/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.JPA;

import dao.interfaces.IInvoiceDAO;
import domain.Invoice;
import domain.Rekeningrijder;
import domain.Vehicle;
import java.util.Calendar;
import java.util.Date;
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
public class InvoiceJPA implements IInvoiceDAO{
    private static final Logger LOGGER = Logger.getLogger(InvoiceJPA.class.getName());
    
    @PersistenceContext(unitName = "TODO")
    private EntityManager em ;

    public InvoiceJPA(){   
    }
    
    public InvoiceJPA(EntityManager em){
        this.em = em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Invoice invoice) {
        em.persist(invoice);
    }

    @Override
    public void edit(Invoice invoice) {
        em.merge(invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        em.remove(invoice);
    }

    @Override
    public Invoice findById(long id) {
        return em.find(Invoice.class, id);
    }

    @Override
    public List<Invoice> findByRekeningrijder(Rekeningrijder rekeningrijder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invoice> findByRekeningrijderMonth(Rekeningrijder rekeningrijder, Calendar date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invoice> findByVehicle(Rekeningrijder rekeningrijder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invoice> findByVehicleMonth(Rekeningrijder rekeningrijder, Calendar date, Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
