/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import domain.Rekeningrijder;
import java.util.List;

/**
 *
 * @author Laurent
 */
public interface IRekeningrijderDAO {
    void create(Rekeningrijder rekeningrijder);
    void edit(Rekeningrijder rekeningrijder);
    void delete(Rekeningrijder rekeningrijder);
    Rekeningrijder findById(long id);
    List<Rekeningrijder> findByUsername(String username);
}
