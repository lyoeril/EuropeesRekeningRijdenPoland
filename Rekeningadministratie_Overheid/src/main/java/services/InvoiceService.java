/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.interfaces.IInvoiceDAO;
import dao.interfaces.IKMRateDAO;
import domain.Invoice;
import domain.KMRate;
import domain.Rekeningrijder;
import domain.Vehicle;
import enums.VehicleType;
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
   
   @Inject
   IKMRateDAO kmRateDAO;
    
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
    
    public Invoice findInvoiceByRekeningrijderMonth(Rekeningrijder rekeningrijder, int year, int month){
        return invoiceDAO.findByRekeningrijderMonth(rekeningrijder, year, month);
    }
    
    public List<Invoice> findInvoiceVehicleMonth(Rekeningrijder rekeningrijder, Calendar date, Vehicle vehicle){
        return invoiceDAO.findByVehicleMonth(rekeningrijder, date, vehicle);
    }
    
    private Invoice calculateInvoice(Rekeningrijder rekeningrijder, Date date, Vehicle vehicle){
        return new Invoice(1, 1, 2017, 1, rekeningrijder);
    }
    
    public void addKMRate(KMRate kmRate){
        kmRateDAO.create(kmRate);
    }
    
    public void updateKMRate(KMRate kmRate){
        kmRateDAO.edit(kmRate);
    }
    
    public void deleteKMRate(KMRate kmRate){
        kmRateDAO.delete(kmRate);
    }
    
    public KMRate findKMRateById(long id){
        return kmRateDAO.findById(id);
    }
    
    public KMRate findKMRateByRegion(String region){
        return kmRateDAO.findByRegion(region);
    }
    
    public KMRate findKMRateByRegionType(String region, VehicleType vehicleType){
        return kmRateDAO.findByRegionVehicleType(region, vehicleType);
    }
}

