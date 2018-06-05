/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import domain.Invoice;
import domain.Rekeningrijder;
import domain.Vehicle;
import enums.InvoiceStatus;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Laurent
 */
public interface IInvoiceDAO {
    void create(Invoice invoice);
    void edit(Invoice invoice);
    void delete(Invoice invoice);
    Invoice findById(long id);
    List<Invoice> findByRekeningrijder(Rekeningrijder rekeningrijder);
    Invoice findByRekeningrijderMonth(Rekeningrijder rekeningrijder, int year, int month);
    List<Invoice> findByVehicle(Rekeningrijder rekeningrijder);
    List<Invoice> findByVehicleMonth(Rekeningrijder rekeningrijder, Calendar date, Vehicle vehicle);
    List<Invoice> findAllInvoices();

    Invoice findInvoiceByCartrackerYearMonth(long cartrackerId, int year, int month);

    List<Invoice> findInvoicesByStatus(InvoiceStatus status);
}
