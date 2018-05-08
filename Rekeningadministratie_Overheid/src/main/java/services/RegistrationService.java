/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.interfaces.ICartrackerDAO;
import dao.interfaces.IRekeningrijderDAO;

/**
 *
 * @author Laurent
 */
public class RegistrationService {
    
    private ICartrackerDAO iCartrackerDAO;
    private IRekeningrijderDAO iRekeningrijderDAO;
    
    public RegistrationService(IRekeningrijderDAO iRekeningrijderDAO, ICartrackerDAO iCartrackerDAO){
        this.iCartrackerDAO = iCartrackerDAO;
        this.iRekeningrijderDAO = iRekeningrijderDAO;
    }
    
    
}
