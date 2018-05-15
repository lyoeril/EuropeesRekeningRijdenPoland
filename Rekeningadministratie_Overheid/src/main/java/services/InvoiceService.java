/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.JPA.KMRateJPA;
import dao.interfaces.IInvoiceDAO;
import domain.Invoice;
import domain.Rekeningrijder;
import domain.Vehicle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Laurent
 */

@Stateless
public class InvoiceService {
    
   @Inject
   IInvoiceDAO invoiceDAO;
    
    public InvoiceService(){
        
    }
    
    public void addInvoice(Invoice invoice){
        invoiceDAO.create(invoice);
    }
    
    public void updateInvoice(Invoice invoice){
        invoiceDAO.edit(invoice);
    }
    
    public void deleteInvoice(Invoice invoice){
        invoiceDAO.delete(invoice);
    }
    
    public Invoice findInvoiceById(long id){
        return invoiceDAO.findById(id);
    }
    
    public List<Invoice> findInvoicesByRekeningrijder(Rekeningrijder rekeningrijder){
        return invoiceDAO.findByRekeningrijder(rekeningrijder);
    }
    
    public List<Invoice> findInvoiceByRekeningrijderMonth(Rekeningrijder rekeningrijder, Calendar date){
        return invoiceDAO.findByRekeningrijderMonth(rekeningrijder, date);
    }
    
    public List<Invoice> findInvoiceVehicleMonth(Rekeningrijder rekeningrijder, Calendar date, Vehicle vehicle){
        return invoiceDAO.findByVehicleMonth(rekeningrijder, date, vehicle);
    }
    
    private Invoice calculateInvoice(Rekeningrijder rekeningrijder, Date date, Vehicle vehicle){
        return new Invoice(1, 1, new GregorianCalendar(), rekeningrijder);
    }
}

