package dao.JPA;

import domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mastermouse
 */
public class UserJpaTest {

    private static UserJPA userJPA;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Rekeningadministratie_Overheid_test");
    private static EntityManager em;

    private User u1;
    private User u2;
    private User u3;

    @Before
    public void setUp() {
        userJPA = new UserJPA();
        em = emf.createEntityManager();
        userJPA.setEm(em);

        u1 = new User("username1", "password1", "email1");
        u1.setId(1L);
        u2 = new User("username2", "password2", "email2");
        u2.setId(2L);
        u3 = new User("username3", "password3", "email3");
        u3.setId(3L);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of setEm method, of class UserJPA.
     */
    @Test
    public void testSetEm() throws Exception {
    }

    /**
     * Test of findAll method, of class UserJPA.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        userJPA.insert(u1);
        userJPA.insert(u2);

        int expResult = 0;
        int result = userJPA.findAll().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findById method, of class UserJPA.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        long id = 1L;
        User expResult = u1;
        userJPA.insert(u1);
        User result = userJPA.findById(id);
        assertEquals(expResult, result);
    }

//    /**
//     * Test of findByEmail method, of class UserJPA.
//     */
//    @Test
//    public void testFindByEmail() throws Exception {
//        u1.setEmail("email1");
//        userJPA.update(u1);
//        System.out.println("findByEmail");
//        String email = "email1";
//        List<User> expResult = null;
//        List<User> result = instance.findByEmail(email);
//        assertEquals(expResult, result);
//    }
    /**
     * Test of findByUsername method, of class UserJPA.
     */
    @Test
    public void testFindByUsername() throws Exception {
        System.out.println("findByUsername");
        String username = "username1";
        userJPA.insert(u1);
        
        User fliep = userJPA.findById(1L);
        List<User> fliepseltjes = userJPA.findByUsername(fliep.getUsername());
        System.out.println("fliep: " + fliep.getUsername());
        
        System.out.println("fliepseltjes: " + fliepseltjes);
        int expResult = 0;
        int result = userJPA.findByUsername(username).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of insert method, of class UserJPA.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        User user = u1;
        userJPA.insert(user);
        boolean expResult = true;
        boolean result = userJPA.insert(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class UserJPA.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        User user = u1;
        userJPA.insert(user);
        user.setEmail("newEmail");
        boolean expResult = true;
        boolean result = userJPA.update(user);
        assertEquals(expResult, result);
    }

}
