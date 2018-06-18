package dao.JPA;

import domain.Cartracker;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mastermouse
 */
public class UserGroupJPATest {
      
    private static CartrackerJPA cartracker;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Rekeningadministratie_Overheid_test");
    private static EntityManager em;
    
@Before
public void setUp() {
        cartracker = new CartrackerJPA();
        em = emf.createEntityManager();
        cartracker.setEm(em); 
    }

@Test
public void testSetEm() throws Exception {
        System.out.println("");
        Cartracker c = new Cartracker("test");
        c.setId(1L);
        cartracker.create(c);
        Cartracker result = cartracker.findById(1L);
        System.out.println("result :  + " + result.getHardware());
        
    }  
}
