/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.JPA;

import domain.Invoice;
import domain.Rekeningrijder;
import domain.Vehicle;
import enums.InvoiceStatus;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class InvoiceJPATest {
    
    
    private static InvoiceJPA invoiceJPA;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Rekeningadministratie_Overheid_test");
    private static EntityManager em;
    
    private Invoice invoice;
    private Rekeningrijder rekeningrijder;

    @Before
    public void setUp() {
        invoiceJPA = new InvoiceJPA();
        em = emf.createEntityManager();
        invoiceJPA.setEm(em);
        
        rekeningrijder = new Rekeningrijder("username", "password", "address", "city", "email");
        invoice = new Invoice(1L, 10, 2018, 01, rekeningrijder);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class InvoiceJPA.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Invoice invoice = this.invoice;
        invoice.setInvoiceNumber(99L);
        InvoiceJPA instance = invoiceJPA;
        instance.create(invoice);
        Invoice result = instance.findById(99L);
        assertEquals(invoice, result);
    }

    /**
     * Test of edit method, of class InvoiceJPA.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Invoice invoice = this.invoice;
        invoice.setInvoiceNumber(3L);
        InvoiceJPA instance = invoiceJPA;
        instance.create(invoice);
        
        Invoice newInv = invoice;
        newInv.setMonth(2);
        instance.edit(invoice);
        assertEquals(2, instance.findById(3L).getMonth());
    }

    /**
     * Test of delete method, of class InvoiceJPA.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Invoice invoice = this.invoice;
        invoice.setInvoiceNumber(1L);
        InvoiceJPA instance = invoiceJPA;
        
        instance.create(invoice);
        instance.delete(invoice);
        Invoice result = instance.findById(1L);
        assertNull(result);
    }

    /**
     * Test of findById method, of class InvoiceJPA.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        long id = 1L;
        InvoiceJPA instance = invoiceJPA;
        invoice.setInvoiceNumber(id);
        instance.create(invoice);
        Invoice expResult = invoice;
        Invoice result = instance.findById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of findByRekeningrijderMonth method, of class InvoiceJPA.
     */
    @Test
    public void testFindByRekeningrijderMonth() throws Exception {
        System.out.println("findByRekeningrijderMonth");
        Rekeningrijder rekeningrijder = this.rekeningrijder;
        int year = invoice.getYear();
        int month = invoice.getMonth();
        InvoiceJPA instance = invoiceJPA;
        instance.create(invoice);
        
        List<Invoice> result = instance.findByRekeningrijderMonth(rekeningrijder, year, month);
        System.out.println("result: " + result);
        boolean containsInvoice = result.contains(invoice);
        assertTrue(!containsInvoice);
    }


    /**
     * Test of findAllInvoices method, of class InvoiceJPA.
     */
    @Test
    public void testFindAllInvoices() throws Exception {
        System.out.println("findAllInvoices");
        InvoiceJPA instance = invoiceJPA;
        instance.create(invoice);
        int expResult = 0;
        List<Invoice> result = instance.findAllInvoices();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of findInvoiceByCartrackerYearMonth method, of class InvoiceJPA.
     */
    @Test
    public void testFindInvoiceByCartrackerYearMonth() throws Exception {
        System.out.println("findInvoiceByCartrackerYearMonth");
        long cartrackerId = 1L;
        int year = 2018;
        int month = 01;
        InvoiceJPA instance = invoiceJPA;
        instance.create(invoice);
        
        Invoice expResult = null;
        Invoice result = instance.findInvoiceByCartrackerYearMonth(cartrackerId, year, month);
        assertEquals(expResult, result);
    }

    /**
     * Test of findInvoicesByStatus method, of class InvoiceJPA.
     */
    @Test
    public void testFindInvoicesByStatus() throws Exception {
        System.out.println("findInvoicesByStatus");
        InvoiceStatus status = InvoiceStatus.OPEN;
        InvoiceJPA instance = invoiceJPA;
        instance.create(invoice);
        Invoice expResult = invoice;
        List<Invoice> result = instance.findInvoicesByStatus(status);
        boolean containsInvoice = !result.contains(expResult);
        assertTrue(containsInvoice);
    }
    
}
