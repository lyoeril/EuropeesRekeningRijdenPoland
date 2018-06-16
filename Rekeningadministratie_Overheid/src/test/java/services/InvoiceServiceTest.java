package services;

import dao.interfaces.IInvoiceDAO;
import dao.interfaces.IKMRateDAO;
import domain.Invoice;
import domain.KMRate;
import domain.Rekeningrijder;
import domain.Vehicle;
import enums.InvoiceStatus;
import enums.VehicleType;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Laurent
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    IInvoiceDAO invoiceDAO;

    @Mock
    IKMRateDAO kmRateDAO;

    public InvoiceServiceTest() {
    }

    private Invoice invoice;
    private Rekeningrijder rekeningrijder;
    private KMRate kMRate;

    @Before
    public void setUp() {
        rekeningrijder = new Rekeningrijder("username", "password", "address", "city", "email");
        invoice = new Invoice(1L, 20, 2018, 06, rekeningrijder);
        kMRate = new KMRate("region");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addInvoice method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddInvoice() throws Exception {
        invoiceService.addInvoice(invoice);
        Mockito.verify(invoiceDAO, Mockito.times(1)).create(invoice);
    }

    /**
     * Test of updateInvoice method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateInvoice() throws Exception {
        invoiceService.updateInvoice(invoice);
        Mockito.verify(invoiceDAO, Mockito.times(1)).edit(invoice);

    }

    /**
     * Test of deleteInvoice method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteInvoice() throws Exception {
        invoiceService.deleteInvoice(invoice);
        Mockito.verify(invoiceDAO, Mockito.times(1)).delete(invoice);
    }

    /**
     * Test of findInvoiceById method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindInvoiceById() throws Exception {
        invoiceService.findInvoiceById(1L);
        Mockito.verify(invoiceDAO, Mockito.times(1)).findById(1L);
    }

    /**
     * Test of findInvoicesByRekeningrijder method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindInvoicesByRekeningrijder() throws Exception {
        invoiceService.findInvoicesByRekeningrijder(rekeningrijder);
        Mockito.verify(invoiceDAO, Mockito.times(1)).findByRekeningrijder(rekeningrijder);
    }

    /**
     * Test of findInvoiceByRekeningrijderMonth method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindInvoiceByRekeningrijderMonth() throws Exception {
        invoiceService.findInvoiceByRekeningrijderMonth(rekeningrijder, 2018, 06);
        Mockito.verify(invoiceDAO, Mockito.times(1)).findByRekeningrijderMonth(rekeningrijder, 2018, 06);

    }

    /**
     * Test of findInvoiceVehicleMonth method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindInvoiceVehicleMonth() throws Exception {
        Calendar date = new GregorianCalendar();
        Vehicle vehicle = new Vehicle(VehicleType.VAN, "licensePlate");
        invoiceService.findInvoiceVehicleMonth(rekeningrijder, date, vehicle);
        Mockito.verify(invoiceDAO, Mockito.times(1)).findByVehicleMonth(rekeningrijder, date, vehicle);
    }

    /**
     * Test of findInvoiceByCartrackerYearMonth method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindInvoiceByCartrackerYearMonth() throws Exception {
        invoiceService.findInvoiceByCartrackerYearMonth(1L, 2018, 06);
        Mockito.verify(invoiceDAO, Mockito.times(1)).findInvoiceByCartrackerYearMonth(1L, 2018, 06);
    }

    /**
     * Test of findAllInvoices method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAllInvoices() throws Exception {
        invoiceService.findAllInvoices();
        Mockito.verify(invoiceDAO, Mockito.times(1)).findAllInvoices();
    }

    /**
     * Test of findInvoicesByStatus method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindInvoicesByStatus() throws Exception {
        invoiceService.findInvoicesByStatus(InvoiceStatus.PAID);
        Mockito.verify(invoiceDAO, Mockito.times(1)).findInvoicesByStatus(InvoiceStatus.PAID);
    }

    /**
     * Test of addKMRate method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddKMRate() throws Exception {
        invoiceService.addKMRate(kMRate);
        Mockito.verify(kmRateDAO, Mockito.times(1)).create(kMRate);
    }

    /**
     * Test of updateKMRate method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateKMRate() throws Exception {
        invoiceService.updateKMRate(kMRate);
        Mockito.verify(kmRateDAO, Mockito.times(1)).edit(kMRate);
    }

    /**
     * Test of deleteKMRate method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteKMRate() throws Exception {
        invoiceService.deleteKMRate(kMRate);
        Mockito.verify(kmRateDAO, Mockito.times(1)).delete(kMRate);
    }

    /**
     * Test of findKMRateById method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindKMRateById() throws Exception {
        invoiceService.findKMRateById(1L);
        Mockito.verify(kmRateDAO, Mockito.times(1)).findById(1L);
    }

    /**
     * Test of findKMRateByRegion method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindKMRateByRegion() throws Exception {
        invoiceService.findKMRateByRegion("region");
        Mockito.verify(kmRateDAO, Mockito.times(1)).findByRegion("region");
    }

    /**
     * Test of findKMRateByRegionType method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindKMRateByRegionType() throws Exception {
        invoiceService.findKMRateByRegionType("region", VehicleType.VAN);
        Mockito.verify(kmRateDAO, Mockito.times(1)).findByRegionVehicleType("region", VehicleType.VAN);
    }

    /**
     * Test of findAllKMRates method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAllKMRates() throws Exception {
        invoiceService.findAllKMRates();
        Mockito.verify(kmRateDAO, Mockito.times(1)).findAll();
    }
}
