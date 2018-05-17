package dao.JPA;

import dao.interfaces.ICartrackerDAO;
import domain.Cartracker;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Laurent
 */

@Stateless
public class CartrackerJPA implements ICartrackerDAO{
    
    private static final Logger LOGGER = Logger.getLogger(CartrackerJPA.class.getName());
    
    @PersistenceContext(unitName = "Rekeningadministratie_Overheid")
    private EntityManager em ;

    public CartrackerJPA(){   
    }
    
    public CartrackerJPA(EntityManager em){
        this.em = em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Cartracker cartracker) {
        em.persist(cartracker);
    }

    @Override
    public void edit(Cartracker cartracker) {
        em.merge(cartracker);
    }

    @Override
    public void delete(Cartracker cartracker) {
        em.remove(cartracker);
    }

    @Override
    public Cartracker findById(long id) {
        return em.find(Cartracker.class, id);
    }
}
