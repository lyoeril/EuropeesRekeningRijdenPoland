/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import domain.Invoice;
import domain.Rekeningrijder;
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
    List<Invoice> findByRekeningrijderMonth(Rekeningrijder rekeningrijder, String month);
    List<Invoice> findByVehicle(Rekeningrijder rekeningrijder);
    List<Invoice> findByVehicleMonth(Rekeningrijder rekeningrijder, String month);
}