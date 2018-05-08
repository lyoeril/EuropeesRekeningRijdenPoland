/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import enums.InvoiceStatus;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Laurent
 */

@Entity
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceNumber;
    private long carTrackerId;
    private double totalAmount;
    
    @Temporal(TemporalType.DATE)
    private Calendar date;
    
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
    
    @ManyToOne
    private Rekeningrijder rekeningrijder;

    public Invoice(long carTrackerId, double totalAmount, Calendar date, Rekeningrijder rekeningrijder) {
        this.carTrackerId = carTrackerId;
        this.totalAmount = totalAmount;
        this.date = date;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Rekeningrijder getRekeningrijder() {
        return rekeningrijder;
    }

    public void setRekeningrijder(Rekeningrijder rekeningrijder) {
        this.rekeningrijder = rekeningrijder;
    }
    
    
    
    
}
