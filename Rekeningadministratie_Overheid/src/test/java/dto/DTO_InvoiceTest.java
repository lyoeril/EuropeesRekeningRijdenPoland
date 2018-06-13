/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.Invoice;
import domain.Rekeningrijder;
import enums.InvoiceStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class DTO_InvoiceTest {
    
    public DTO_InvoiceTest() {
    }
    
    private Invoice i;
    
    private long cartrackerId;
    private double totalAmount;
    private int year;
    private int month;
    private Rekeningrijder rekeningrijder;
    
    private DTO_Invoice dtoInvoice;
    
    
    
    @Before
    public void setUp() {
        cartrackerId = 1L;
        totalAmount = 1.1;
        year = 2018;
        month = 01;
        rekeningrijder = new Rekeningrijder();
        i = new Invoice(cartrackerId, totalAmount, year, month, rekeningrijder);
        
        dtoInvoice = new DTO_Invoice(i);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class DTO_Invoice.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DTO_Invoice instance = dtoInvoice;
        long expResult = 0L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class DTO_Invoice.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 2L;
        DTO_Invoice instance = dtoInvoice;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getTotalAmount method, of class DTO_Invoice.
     */
    @Test
    public void testGetTotalAmount() {
        System.out.println("getTotalAmount");
        DTO_Invoice instance = dtoInvoice;
        double expResult = 1.1;
        double result = instance.getTotalAmount();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTotalAmount method, of class DTO_Invoice.
     */
    @Test
    public void testSetTotalAmount() {
        System.out.println("setTotalAmount");
        double totalAmount = 2.0;
        DTO_Invoice instance = dtoInvoice;
        instance.setTotalAmount(totalAmount);
        assertEquals(totalAmount, instance.getTotalAmount(), 0.0);
    }

    /**
     * Test of getStatus method, of class DTO_Invoice.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        DTO_Invoice instance = dtoInvoice;
        InvoiceStatus expResult = InvoiceStatus.OPEN;
        InvoiceStatus result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class DTO_Invoice.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        InvoiceStatus status = InvoiceStatus.CANCELED;
        DTO_Invoice instance = dtoInvoice;
        instance.setStatus(status);
        assertEquals(status, instance.getStatus());
    }

    /**
     * Test of getYear method, of class DTO_Invoice.
     */
    @Test
    public void testGetYear() {
        System.out.println("getYear");
        DTO_Invoice instance = dtoInvoice;
        int expResult = 2018;
        int result = instance.getYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of setYear method, of class DTO_Invoice.
     */
    @Test
    public void testSetYear() {
        System.out.println("setYear");
        int year = 2019;
        DTO_Invoice instance = dtoInvoice;
        instance.setYear(year);
        assertEquals(year, instance.getYear());
    }

    /**
     * Test of getMonth method, of class DTO_Invoice.
     */
    @Test
    public void testGetMonth() {
        System.out.println("getMonth");
        DTO_Invoice instance = dtoInvoice;
        int expResult = 01;
        int result = instance.getMonth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMonth method, of class DTO_Invoice.
     */
    @Test
    public void testSetMonth() {
        System.out.println("setMonth");
        int month = 02;
        DTO_Invoice instance = dtoInvoice;
        instance.setMonth(month);
        assertEquals(month, instance.getMonth());
    }

    /**
     * Test of getCartrackerId method, of class DTO_Invoice.
     */
    @Test
    public void testGetCartrackerId() {
        System.out.println("getCartrackerId");
        DTO_Invoice instance = dtoInvoice;
        long expResult = 1L;
        long result = instance.getCartrackerId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCartrackerId method, of class DTO_Invoice.
     */
    @Test
    public void testSetCartrackerId() {
        System.out.println("setCartrackerId");
        long cartrackerId = 2L;
        DTO_Invoice instance = dtoInvoice;
        instance.setCartrackerId(cartrackerId);
        assertEquals(cartrackerId, instance.getCartrackerId());
    }
    
}
