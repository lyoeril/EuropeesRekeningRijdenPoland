/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import enums.InvoiceStatus;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class InvoiceTest {
    private Rekeningrijder r1;
    private Rekeningrijder r2;
    
    private Cartracker c1;
    private Cartracker c2;
    private Cartracker c3;
    private Cartracker c4;
    
    private Invoice i1;
    private Invoice i2;
    private Invoice i3;
    private Invoice i4;
    
    public InvoiceTest() {
    }
    
    @Before
    public void setUp() {
        r1 = new Rekeningrijder("r1", "address1", "","", "");
        r2 = new Rekeningrijder("r2", "address2", "", "", "");
        
        c1 = new Cartracker("hardware1");
        c1.setId(1L);
        c2 = new Cartracker("hardware2");
        c2.setId(2L);
        c3 = new Cartracker("hardware3");
        c3.setId(3L);
        c4 = new Cartracker("hardware4");
        c4.setId(4L);
        
        i1 = new Invoice(1L, 11.11, 2017, 1, r1);
        i1.setInvoiceNumber(1L);
        i2 = new Invoice(2L, 22.22, 2017, 1, r2);
        i2.setInvoiceNumber(2L);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInvoiceNumber method, of class Invoice.
     */
    @Test
    public void testGetInvoiceNumber() {
        System.out.println("getInvoiceNumber");
        Invoice instance = i1;
        long expResult = 1L;
        long result = instance.getInvoiceNumber();
        
        instance = i2;
        expResult = 2L;
        result = instance.getInvoiceNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInvoiceNumber method, of class Invoice.
     */
    @Test
    public void testSetInvoiceNumber() {
        System.out.println("setInvoiceNumber");
        long invoiceNumber = 99L;
        Invoice instance = i1;
        instance.setInvoiceNumber(invoiceNumber);        
        assertEquals(invoiceNumber, instance.getInvoiceNumber());
    }

    /**
     * Test of getCarTrackerId method, of class Invoice.
     */
    @Test
    public void testGetCarTrackerId() {
        System.out.println("getCarTrackerId");
        Invoice instance = i1;
        long expResult = 1L;
        long result = instance.getCarTrackerId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCarTrackerId method, of class Invoice.
     */
    @Test
    public void testSetCarTrackerId() {
        System.out.println("setCarTrackerId");
        long carTrackerId = 99L;
        Invoice instance = i1;
        instance.setCarTrackerId(carTrackerId);
        assertEquals(carTrackerId, instance.getCarTrackerId());
    }

    /**
     * Test of getTotalAmount method, of class Invoice.
     */
    @Test
    public void testGetTotalAmount() {
        System.out.println("getTotalAmount");
        Invoice instance = i1;
        double expResult = 11.11;
        double result = instance.getTotalAmount();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getStatus method, of class Invoice.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Invoice instance = i1;
        InvoiceStatus expResult = InvoiceStatus.OPEN;
        InvoiceStatus result = instance.getStatus();
        assertEquals(expResult, result);  
    }

    /**
     * Test of setStatus method, of class Invoice.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        InvoiceStatus status = InvoiceStatus.CANCELED;
        Invoice instance = i1;
        instance.setStatus(status);
        assertEquals(status, instance.getStatus());
        
        status = InvoiceStatus.PAID;
        instance.setStatus(status);
        assertEquals(status, instance.getStatus());
        
        status = InvoiceStatus.UNKNOWN;
        instance.setStatus(status);
        assertEquals(status, instance.getStatus());
        
        status = InvoiceStatus.OPEN;
        instance.setStatus(status);
        assertEquals(status, instance.getStatus());
    }

    /**
     * Test of getRekeningrijder method, of class Invoice.
     */
    @Test
    public void testGetRekeningrijder() {
        System.out.println("getRekeningrijder");
        Invoice instance = i1;
        Rekeningrijder expResult = r1;
        Rekeningrijder result = instance.getRekeningrijder();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRekeningrijder method, of class Invoice.
     */
    @Test
    public void testSetRekeningrijder() {
        System.out.println("setRekeningrijder");
        Rekeningrijder rekeningrijder = r2;
        Invoice instance = i1;
        instance.setRekeningrijder(rekeningrijder);
        assertEquals(r2, i1.getRekeningrijder());
    }
    
}
