/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.InvoiceStatus;
import java.io.Serializable;
import java.util.Calendar;
import javax.enterprise.inject.Model;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Laurent
 */

@Entity
@Model
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll",
            query = "SELECT i FROM Invoice i")    ,
    @NamedQuery(name = "Invoice.findByRekeningrijderMonth",
            query = "SELECT i FROM Invoice i WHERE i.rekeningrijder.id = :id "
                    + "AND i.year = :year AND i.month = :month"),
    @NamedQuery(name = "Invoice.findByCartrackerYearMonth",
            query = "SELECT i FROM Invoice i WHERE i.carTrackerId = :cartrackerid AND i.year = :year AND i.month = :month"),   
    @NamedQuery(name = "Invoice.findByStatus",
            query = "SELECT i FROM Invoice i WHERE i.status = :status")
}) 
public class Invoice implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceNumber;
    private long carTrackerId;
    private double totalAmount;
    
    private int year;
    private int month;
    
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
    
    @ManyToOne
    private Rekeningrijder rekeningrijder;
    
    //JPA
    public Invoice(){
        
    }

    public Invoice(long carTrackerId, double totalAmount, int year, int month, Rekeningrijder rekeningrijder) {
        this.carTrackerId = carTrackerId;
        this.totalAmount = totalAmount;
        this.year = year;
        this.month = month;
        this.rekeningrijder = rekeningrijder;
        status = InvoiceStatus.OPEN;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public long getCarTrackerId() {
        return carTrackerId;
    }

    public void setCarTrackerId(long carTrackerId) {
        this.carTrackerId = carTrackerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }    

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    @JsonIgnore
    public Rekeningrijder getRekeningrijder() {
        return rekeningrijder;
    }

    public void setRekeningrijder(Rekeningrijder rekeningrijder) {
        this.rekeningrijder = rekeningrijder;
    }
    
    
    
    
}
