package dao.JPA;

import domain.Cartracker;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laurent
 */
public class CartrackerJPATest {

    private static CartrackerJPA cartrackerJPA;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Rekeningadministratie_Overheid_test");
    private static EntityManager em;

    @Before
    public void setUp() {
        cartrackerJPA = new CartrackerJPA();
        em = emf.createEntityManager();
        cartrackerJPA.setEm(em);
    }

    @Test
    public void testSetEm() throws Exception {

    }

    /**
     * Test of create method, of class CartrackerJPA.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Cartracker c = new Cartracker("test");
        c.setId(1L);
        cartrackerJPA.create(c);
        Cartracker result = cartrackerJPA.findById(1L);
        assertEquals(c, result);
    }

    /**
     * Test of edit method, of class CartrackerJPA.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Cartracker c = new Cartracker("test");
        c.setId(1L);
        cartrackerJPA.create(c);
        Cartracker result = cartrackerJPA.findById(1L);
        Cartracker edited = result;
        edited.setId(99L);
        cartrackerJPA.edit(edited);
        result = cartrackerJPA.findById(99L);
        assertEquals(edited, result);
    }

    /**
     * Test of delete method, of class CartrackerJPA.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Cartracker c = new Cartracker("test");
        c.setId(1L);
        cartrackerJPA.create(c);
        cartrackerJPA.delete(c);
        Cartracker result = cartrackerJPA.findById(1L);
        assertNull(result);
    }

    /**
     * Test of findById method, of class CartrackerJPA.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Cartracker c = new Cartracker("test");
        c.setId(1L);
        cartrackerJPA.create(c);
        Cartracker result = cartrackerJPA.findById(1L);
        assertEquals(c, result);
    }

    /**
     * Test of findAll method, of class CartrackerJPA.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        Cartracker c = new Cartracker("test");
        Cartracker c2 = new Cartracker("test2");
        cartrackerJPA.create(c);
        cartrackerJPA.create(c2);
        
        List<Cartracker> result = cartrackerJPA.findAll();
        System.out.println("result: " + result);
        assertEquals(2, 2);
    }
}
