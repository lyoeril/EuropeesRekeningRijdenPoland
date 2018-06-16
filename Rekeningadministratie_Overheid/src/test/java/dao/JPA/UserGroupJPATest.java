package dao.JPA;

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
      
    private static UserJPA userjpa;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Rekeningadministratie_Overheid_Test");
    private static EntityManager em;
    
@Before
public void setUp() {
        userjpa = new UserJPA();
        em = emf.createEntityManager();
        userjpa.setEm(em); 
    }

//@Test
//public void testSetEm() throws Exception {
//        System.out.println("");
//        
//    }  
}
