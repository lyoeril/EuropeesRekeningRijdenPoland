/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.interfaces.ICartrackerDAO;
import dao.interfaces.IRekeningrijderDAO;
import dao.interfaces.IUserGroupDAO;
import domain.Cartracker;
import domain.Rekeningrijder;
import domain.UserGroup;
import java.util.List;
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
    
    @Inject
    IUserGroupDAO iUserGroupDAO;

    public RegistrationService() {
    }    
    
    public RegistrationService(IRekeningrijderDAO iRekeningrijderDAO, ICartrackerDAO iCartrackerDAO,
                                IUserGroupDAO iUserGroupDAO){
        this.iCartrackerDAO = iCartrackerDAO;
        this.iRekeningrijderDAO = iRekeningrijderDAO;
        this.iUserGroupDAO = iUserGroupDAO;
    }
    
    //Rekeningrijder Management
    
    public void addRekeningrijder(Rekeningrijder rekeningrijder){
        try{
            iRekeningrijderDAO.create(rekeningrijder);
        }catch(Exception e){
            System.out.println("e = EEEEENEN EXCPEITONEIASDN" + e);
        }
        
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
    
    public List<Cartracker> findAllCartrackers(){
        return iCartrackerDAO.findAll();
    }
    
    //UserGroup
    public List<UserGroup> findAllUserGroups(){
        return iUserGroupDAO.findAll();
    }
    
    public UserGroup findByName(String usergroup){
        return iUserGroupDAO.findByName(usergroup);
    }
}
