//package dao.JPA;
//
//import domain.Cartracker;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Laurent
// */
//public class CartrackerJPATest {
//
//    private static CartrackerJPA cartracker;
//    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Rekeningadministratie_Overheid_test");
//    private static EntityManager em;
//
//    @Before
//    public void setUp() {
//        cartracker = new CartrackerJPA();
//        em = emf.createEntityManager();
//        cartracker.setEm(em);
//    }
//
//    @Test
//    public void testSetEm() throws Exception {
//    
//
//    }
//
//    /**
//     * Test of create method, of class CartrackerJPA.
//     */
//    @Test
//    public void testCreate() throws Exception {
//    System.out.println("create");
//        Cartracker c = new Cartracker("test");
//        c.setId(1L);
//        cartracker.create(c);
//        Cartracker result = cartracker.findById(1L);
//        System.out.println("result :  + " + result.getHardware());
//    }
////
////    /**
////     * Test of edit method, of class CartrackerJPA.
////     */
////    @Test
////    public void testEdit() throws Exception {
////        System.out.println("edit");
////        Cartracker cartracker = null;
////        CartrackerJPA instance = new CartrackerJPA();
////        instance.edit(cartracker);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of delete method, of class CartrackerJPA.
////     */
////    @Test
////    public void testDelete() throws Exception {
////        System.out.println("delete");
////        Cartracker cartracker = null;
////        CartrackerJPA instance = new CartrackerJPA();
////        instance.delete(cartracker);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of findById method, of class CartrackerJPA.
////     */
////    @Test
////    public void testFindById() throws Exception {
////        System.out.println("findById");
////        long id = 0L;
////        CartrackerJPA instance = new CartrackerJPA();
////        Cartracker expResult = null;
////        Cartracker result = instance.findById(id);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of findAll method, of class CartrackerJPA.
////     */
////    @Test
////    public void testFindAll() throws Exception {
////        System.out.println("findAll");
////        CartrackerJPA instance = new CartrackerJPA();
////        List<Cartracker> expResult = null;
////        List<Cartracker> result = instance.findAll();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
//}
