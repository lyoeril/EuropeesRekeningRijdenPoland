/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import domain.Cartracker;
import java.util.List;

/**
 *
 * @author Laurent
 */
public interface ICartrackerDAO {

    void create(Cartracker cartracker);
    void edit(Cartracker cartracker);
    void delete(Cartracker cartracker);
    Cartracker findById(long id);

    List<Cartracker> findAll();
}
