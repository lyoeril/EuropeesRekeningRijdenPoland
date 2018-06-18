/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.Invoice;
import enums.InvoiceStatus;
import java.util.Calendar;

/**
 *
 * @author Laurent
 */
public class DTO_Invoice {

    private long id;
    private long cartrackerId;
    private double totalAmount;
    private InvoiceStatus status;
    private int year;
    private int month;
    private long rekeningrijderId;

    public DTO_Invoice(Invoice invoice) {
        this.id = invoice.getInvoiceNumber();
        this.totalAmount = invoice.getTotalAmount();
        this.status = invoice.getStatus();
        this.year = invoice.getYear();
        this.month = invoice.getMonth();
        this.cartrackerId = invoice.getCarTrackerId();
        this.rekeningrijderId = invoice.getRekeningrijder().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
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

    public long getCartrackerId() {
        return cartrackerId;
    }

    public void setCartrackerId(long cartrackerId) {
        this.cartrackerId = cartrackerId;
    }

    public long getRekeningrijderId() {
        return rekeningrijderId;
    }

    public void setRekeningrijderId(long rekeningrijderId) {
        this.rekeningrijderId = rekeningrijderId;
    }
    
    

}
