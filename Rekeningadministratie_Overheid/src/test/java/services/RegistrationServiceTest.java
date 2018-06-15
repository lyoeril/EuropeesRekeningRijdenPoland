/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.JPA.RekeningrijderJPA;
import dao.interfaces.ICartrackerDAO;
import dao.interfaces.IRekeningrijderDAO;
import dao.interfaces.IUserGroupDAO;
import dao.interfaces.IVehicleDAO;
import domain.Cartracker;
import domain.Rekeningrijder;
import domain.UserGroup;
import domain.Vehicle;
import enums.VehicleType;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Laurent
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    ICartrackerDAO iCartrackerDAO;

    @Mock
    IRekeningrijderDAO iRekeningrijderDAO;

    @Mock
    IUserGroupDAO iUserGroupDAO;

    @Mock
    IVehicleDAO iVehicleDAO;

    public RegistrationServiceTest() {
    }

    private Rekeningrijder rekeningrijder;
    private Vehicle vehicle;
    private Cartracker cartracker;
    private UserGroup userGroup;

    @Before
    public void setUp() {
        rekeningrijder = new Rekeningrijder("username", "password", "address", "city", "email");
        vehicle = new Vehicle(VehicleType.VAN, "licensePlate");
        cartracker = new Cartracker("hardware");
        userGroup = new UserGroup("userGroup");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addRekeningrijder method, of class RegistrationService.
     */
    @Test
    public void testAddRekeningrijder() throws Exception {
        System.out.println("nice");
        registrationService.addRekeningrijder(rekeningrijder);
        Mockito.verify(iRekeningrijderDAO, Mockito.times(1)).create(rekeningrijder);
    }

    /**
     * Test of updateRekeningrijder method, of class RegistrationService.
     */
    @Test
    public void testUpdateRekeningrijder() throws Exception {
        System.out.println("nice");
        registrationService.addRekeningrijder(rekeningrijder);
        rekeningrijder.setUsername("new Username");
        registrationService.updateRekeningrijder(rekeningrijder);
        Mockito.verify(iRekeningrijderDAO, Mockito.times(1)).edit(rekeningrijder);
    }

    /**
     * Test of deleteRekeningrijder method, of class RegistrationService.
     */
    @Test
    public void testDeleteRekeningrijder() throws Exception {
        registrationService.addRekeningrijder(rekeningrijder);
        registrationService.deleteRekeningrijder(rekeningrijder);
        Mockito.verify(iRekeningrijderDAO, Mockito.times(1)).delete(rekeningrijder);
    }

    /**
     * Test of findRekeningrijderById method, of class RegistrationService.
     */
    @Test
    public void testFindRekeningrijderById() throws Exception {
        rekeningrijder.setId(1L);
        registrationService.addRekeningrijder(rekeningrijder);
        registrationService.findRekeningrijderById(1L);
        Mockito.verify(iRekeningrijderDAO, Mockito.times(1)).findById(1L);
    }

    /**
     * Test of addCartracker method, of class RegistrationService.
     */
    @Test
    public void testAddCartracker() throws Exception {
        registrationService.addCartracker(cartracker);
        Mockito.verify(iCartrackerDAO, Mockito.times(1)).create(cartracker);
    }

    /**
     * Test of updateCartracker method, of class RegistrationService.
     */
    @Test
    public void testUpdateCartracker() throws Exception {
        registrationService.addCartracker(cartracker);
        cartracker.setHardware("newHardware");
        registrationService.updateCartracker(cartracker);
        Mockito.verify(iCartrackerDAO, Mockito.times(1)).edit(cartracker);
    }

    /**
     * Test of deleteCartracker method, of class RegistrationService.
     */
    @Test
    public void testDeleteCartracker() throws Exception {
        registrationService.addCartracker(cartracker);
        registrationService.deleteCartracker(cartracker);
        Mockito.verify(iCartrackerDAO, Mockito.times(1)).delete(cartracker);
    }

    /**
     * Test of findCartrackerById method, of class RegistrationService.
     */
    @Test
    public void testFindCartrackerById() throws Exception {
        cartracker.setId(1L);
        registrationService.addCartracker(cartracker);
        registrationService.findCartrackerById(1L);
        Mockito.verify(iCartrackerDAO, Mockito.times(1)).findById(1L);
    }

    /**
     * Test of findAllCartrackers method, of class RegistrationService.
     */
    @Test
    public void testFindAllCartrackers() throws Exception {
        Cartracker c2 = new Cartracker("hardware2");
        registrationService.addCartracker(cartracker);
        registrationService.addCartracker(c2);
        registrationService.findAllCartrackers();
        Mockito.verify(iCartrackerDAO, Mockito.times(1)).findAll();
    }

    /**
     * Test of findAllUserGroups method, of class RegistrationService.
     */
    @Test
    public void testFindAllUserGroups() throws Exception {
        registrationService.findAllUserGroups();
        Mockito.verify(iUserGroupDAO, Mockito.times(1)).findAll();
    }

    /**
     * Test of findByName method, of class RegistrationService.
     */
    @Test
    public void testFindByName() throws Exception {
        registrationService.findByName("userGroup");
        Mockito.verify(iUserGroupDAO, Mockito.times(1)).findByName("userGroup");
    }

    /**
     * Test of findAllVehicles method, of class RegistrationService.
     */
    @Test
    public void testFindAllVehicles() throws Exception {
        registrationService.findAllVehicles();
        Mockito.verify(iVehicleDAO, Mockito.times(1)).findAllVehicles();
    }

    /**
     * Test of findVehicleById method, of class RegistrationService.
     */
    @Test
    public void testFindVehicleById() throws Exception {
        registrationService.findVehicleById(1L);
        Mockito.verify(iVehicleDAO, Mockito.times(1)).findVehicleById(1L);
    }

    /**
     * Test of updateVehicle method, of class RegistrationService.
     */
    @Test
    public void testUpdateVehicle() throws Exception {
        registrationService.updateVehicle(vehicle);
        Mockito.verify(iVehicleDAO, Mockito.times(1)).edit(vehicle);
    }

    /**
     * Test of findVehicleByLicenseplate method, of class RegistrationService.
     */
    @Test
    public void testFindVehicleByLicenseplate() throws Exception {
        registrationService.findVehicleByLicenseplate("licensePlate");
        Mockito.verify(iVehicleDAO, Mockito.times(1)).findVehicleByLicenseplate("licensePlate");
    }

}
