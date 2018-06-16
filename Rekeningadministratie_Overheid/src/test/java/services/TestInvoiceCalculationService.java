/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.interfaces.IInvoiceDAO;
import dao.interfaces.IKMRateDAO;
import domain.Location;
import domain.Rekeningrijder;
import enums.VehicleType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Laurent
 */
@RunWith(MockitoJUnitRunner.class)
public class TestInvoiceCalculationService {

    @InjectMocks
    private InvoiceCalculationService ics;

    @Mock
    InvoiceService invoiceService;

    @Test
    public void test() {
        Calendar c1 = new GregorianCalendar(2018, 01, 01);
        Location l1 = new Location(1L, c1, 52.40533, 19.27417);
        Location l2 = new Location(2L, c1, 52.23478, 19.17059);
        
        List<Location> locations = new ArrayList<>();
        locations.add(l1);
        locations.add(l2);
        
        ics.calculateInvoice(3L, 1, 2018, new Rekeningrijder(), locations, VehicleType.VAN);
    }

}
