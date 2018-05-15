/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.interfaces.ICartrackerDAO;
import dao.interfaces.IRekeningrijderDAO;
import domain.Cartracker;
import domain.Rekeningrijder;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Laurent
 */

@Stateless
public class RegistrationService {
    
    @Inject
    ICartrackerDAO iCartrackerDAO;
    
    @Inject
    IRekeningrijderDAO iRekeningrijderDAO;

    public RegistrationService() {
    }    
    
    public RegistrationService(IRekeningrijderDAO iRekeningrijderDAO, ICartrackerDAO iCartrackerDAO){
        this.iCartrackerDAO = iCartrackerDAO;
        this.iRekeningrijderDAO = iRekeningrijderDAO;
    }
    
    //Rekeningrijder Management
    
    public void addRekeningrijder(Rekeningrijder rekeningrijder){
        iRekeningrijderDAO.create(rekeningrijder);
    }
    
    public void updateRekeningrijder(Rekeningrijder rekeningrijder){
        iRekeningrijderDAO.edit(rekeningrijder);
    }
    
    public void deleteRekeningrijder(Rekeningrijder rekeningrijder){
        iRekeningrijderDAO.delete(rekeningrijder);
    }
    
    public Rekeningrijder findRekeningrijderById(long id){
        return iRekeningrijderDAO.findById(id);
    }
    
    
    //Cartracker management
    
    public void addCartracker(Cartracker cartracker){
        iCartrackerDAO.create(cartracker);
    }
    
    public void updateCartracker(Cartracker cartracker){
        iCartrackerDAO.edit(cartracker);
    }
    
    public void deleteCartracker(Cartracker cartracker){
        iCartrackerDAO.delete(cartracker);
    }
    
    public Cartracker findCartrackerById(long id){
        return iCartrackerDAO.findById(id);
    }
}
