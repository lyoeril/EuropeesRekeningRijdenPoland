/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import domain.Cartracker;
import domain.Invoice;
import domain.KMRate;
import domain.Rekeningrijder;
import domain.User;
import domain.Vehicle;
import dto.DTO_User;
import dto.DTO_Vehicle;
import enums.InvoiceStatus;
import enums.VehicleType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.resource.spi.SecurityPermission;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import services.InvoiceService;
import services.RegistrationService;
import services.UserService;

/**
 *
 * @author Laurent
 */
@Path("overheid")
@JWTTokenNeeded
@Stateless
@Produces(APPLICATION_JSON)
public class OverheidAPI {

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private RegistrationService registrationService;

    @Inject
    private UserService userService;

//    @GET
//    @Produces(APPLICATION_JSON)
//    public Response getTest(){
//        return Response.accepted("test").build();
//    }
//    
    @GET
    @Produces(APPLICATION_JSON)
    public Response getEmployee(
            @Context HttpHeaders headers,
            @Context SecurityContext securityContext) {
        System.out.println("hier 1");
        if(!isOverheid(securityContext)){return Response.status(Status.UNAUTHORIZED).build();}
        System.out.println("hier 2");
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        System.out.println("hier 3");
        User u = this.getUserFromToken(token);
        System.out.println("hier 4");
        if(u!= null){
            System.out.println("hier 5");
            return Response.accepted().build();
        }
//        boolean isRekeningrijder = Rekeningrijder.class.isAssignableFrom(u.getClass());
//        if (u != null) {
//            if (!isRekeningrijder) {
//                return Response.accepted(new DTO_User(u)).build();
//            }
//        }
        return Response.status(Status.FORBIDDEN).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("cartrackers")
    public Response getCartrackers() {
        
        List<Cartracker> cartrackers = registrationService.findAllCartrackers();
        if (cartrackers != null) {
            return Response.accepted(cartrackers).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Path("cartrackers/new")
    public Response addCartracker(
            @Context HttpHeaders headers,
            @FormParam("hardware") String hardware) {
        if (!hardware.isEmpty()) {
            Cartracker cartracker = new Cartracker(hardware);
            registrationService.addCartracker(cartracker);
            return Response.accepted(cartracker).build();
        }
        return Response.status(Status.BAD_REQUEST).build();

    }

    @GET
    @Path("cartrackers/{id}")
    public Response getCartrackerById(@PathParam("id") long id) {
        Cartracker c = registrationService.findCartrackerById(id);

        if (c != null) {
            return Response.accepted(c).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Produces(APPLICATION_JSON)
    @Path("cartrackers/{id}/update")
    public Response setCartracker(
            @PathParam("id") long id,
            @FormParam("hardware") String hardware) {
        Cartracker c = registrationService.findCartrackerById(id);
        if (c != null) {
            c.setHardware(hardware);
            registrationService.updateCartracker(c);
            return Response.accepted(c).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Path("KMRates")
    public Response getKMRates() {
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("KMRates/{region}")
    public Response getKMRateByRegion(
            @PathParam("region") String region) {
        if (!region.isEmpty()) {
            KMRate kMRate = invoiceService.findKMRateByRegion(region);
            if (kMRate != null) {
                return Response.accepted(kMRate).build();
            }
        }
        return Response.status(Status.BAD_REQUEST).build();
    }
//    
//    @POST
//    @Produces(APPLICATION_JSON)
//    @Path("KMRates/{region}/{vehicleType}")
//    public Response newKMRateForVehicleType(
//            @PathParam("region") String region,
//            @PathParam("vehicleType") VehicleType vehicleType,
//            @FormParam("rate") double rate){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    @POST
//    @Path("KMRates/{region}")
//    public Response setKMRateByRegion(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    

    @GET
    @Path("invoices")
    public Response getCalculatedInvoices() {
        List<Invoice> invoices = invoiceService.findAllInvoices();
        if (invoices != null) {
            return Response.accepted(invoices).build();
        }
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }
//    
//    @POST
//    @Path("invoices/vehicle/{year}/{month}")
//    public Response reCalculateInvoice(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    

    @GET
    @Path("invoices/{id}")
    public Response getInvoiceById(
            @PathParam("id") long id) {
        Invoice i = invoiceService.findInvoiceById(id);
        if (i != null) {
            return Response.accepted(i).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("invoices/{id}/update")
    public Response updateInvoice(
            @PathParam("id") long id,
            @FormParam("status") InvoiceStatus status) {
        Invoice i = invoiceService.findInvoiceById(id);
        if (i != null && status != null) {
            i.setStatus(status);
            invoiceService.updateInvoice(i);
            return Response.accepted(i).build();
        }
        return Response.notModified("Couldn't update invoice").build();

    }

    @GET
    @Path("invoices/cartracker/{cartrackerId}/{year}/{month}")
    public Response getInvoicesByCartrackerYearMonth(
            @PathParam("cartrackerId") long id,
            @PathParam("year") int year,
            @PathParam("month") int month) {
        Invoice i = invoiceService.findInvoiceByCartrackerYearMonth(id, year, month);
        if (i != null) {
            return Response.accepted(i).build();
        }
        return Response.status(Status.FORBIDDEN).build();
    }

    @GET
    @Path("invoices/rekeningrijder/{id}")
    public Response getInvoicesByRekeningrijder(
            @PathParam("id") long id) {
        Rekeningrijder r = registrationService.findRekeningrijderById(id);
        List<Invoice> invoices = r.getInvoices();
        if (invoices != null) {
            return Response.accepted(invoices).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Path("invoices/invoicestatus/{status}")
    public Response getInvoicesByStatus(
            @PathParam("status") InvoiceStatus status) {
        List<Invoice> invoices = invoiceService.findInvoicesByStatus(status);
        if (invoices != null) {
            return Response.accepted(invoices).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }
    
    @GET
    @Path("vehicles")
    public Response getVehicles(){
        List<Vehicle> vehicles = registrationService.findAllVehicles();
        List<DTO_Vehicle> vehiclesDTO = new ArrayList<>();
        if(vehicles != null){
            for(Vehicle v: vehicles){
                vehiclesDTO.add(new DTO_Vehicle(v));
            }
            return Response.accepted(vehiclesDTO).build();
        }
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }
    
    @GET
    @Path("vehicles/{id}")
    public Response getVehicleById(
            @PathParam("id") long id){
        Vehicle v = registrationService.findVehicleById(id);
        if(v != null){
            return Response.accepted(new DTO_Vehicle(v)).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }
    
    @PUT
    @Path("vehicles/{id}/update")
    public Response updateVehicle(
            @PathParam("id") long id,
            @FormParam("licensePlate") String licensePlate,
            @FormParam("vehicleType") VehicleType vehicleType,
            @FormParam("cartrackerId") long cartrackerId){        
        Vehicle v = registrationService.findVehicleById(id);
        Cartracker c = registrationService.findCartrackerById(cartrackerId);
        if(v != null){
            v.setLicensePlate(licensePlate);
            v.setVehicleType(vehicleType);
            if(c != null){
                v.setCartracker(c);
            }
            registrationService.updateVehicle(v);
            return Response.accepted(v).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    private String getUsernameFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512("supersecret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("mario")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            final String username = jwt.getSubject();
            return username;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Rekeningrijder getRekeningrijderFromUsername(String username) {
        User u = userService.findByUsername(username).get(0);

        if (u == null) {
            return null;
        }
        Rekeningrijder rekeningrijder = registrationService.findRekeningrijderById(u.getId());
        return rekeningrijder;
    }

    private User getUserFromUsername(String username) {
        User u = userService.findByUsername(username).get(0);
        return u;
    }

    private User getUserFromToken(String token) {
        String username = this.getUsernameFromToken(token);
        return this.getUserFromUsername(username);
    }

    private Rekeningrijder getRekeningrijderFromToken(String token) {
        String username = this.getUsernameFromToken(token);
        return this.getRekeningrijderFromUsername(username);
    }
    
    private boolean isOverheid(SecurityContext context){
        if(context.isUserInRole("OVERHEID")){
            return true;
        }
        return false;
    }
}
