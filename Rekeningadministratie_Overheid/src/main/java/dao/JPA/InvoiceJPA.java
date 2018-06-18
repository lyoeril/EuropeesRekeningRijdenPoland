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
import enums.InvoiceStatus;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Laurent
 */
@Stateless
public class InvoiceJPA implements IInvoiceDAO {

    private static final Logger LOGGER = Logger.getLogger(InvoiceJPA.class.getName());

    @PersistenceContext(unitName = "Rekeningadministratie_Overheid")
    private EntityManager em;

    public InvoiceJPA() {
    }

    public InvoiceJPA(EntityManager em) {
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
    public List<Invoice> findByRekeningrijderMonth(Rekeningrijder rekeningrijder, int year, int month) {
        try {
            Query q = em.createNamedQuery("Invoice.findByRekeningrijderMonth");
            q.setParameter("id", rekeningrijder.getId());
            q.setParameter("year", year);
            q.setParameter("month", month);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Invoice> findAllInvoices() {
        return em.createNamedQuery("Invoice.findAll").getResultList();
    }

    @Override
    public Invoice findInvoiceByCartrackerYearMonth(long cartrackerId, int year, int month) {
         try {
            Query q = em.createNamedQuery("Invoice.findByCartrackerYearMonth");
            q.setParameter("cartrackerid", cartrackerId);
            q.setParameter("year", year);
            q.setParameter("month", month);
            return (Invoice)q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Invoice> findInvoicesByStatus(InvoiceStatus status) {
         try {
            Query q = em.createNamedQuery("Invoice.findByStatus");
            q.setParameter("status", status);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    
    
    
    
    
    

}
