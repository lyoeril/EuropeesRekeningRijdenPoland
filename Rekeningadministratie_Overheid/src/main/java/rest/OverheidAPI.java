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
import domain.Ride;
import domain.User;
import domain.Vehicle;
import dto.DTO_Cartracker;
import dto.DTO_Invoice;
import dto.DTO_KMRate;
import dto.DTO_Rekeningrijder;
import dto.DTO_User;
import dto.DTO_Vehicle;
import enums.InvoiceStatus;
import enums.VehicleType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.glassfish.hk2.utilities.reflection.Logger;
import services.InvoiceCalculationService;
import services.InvoiceService;
import services.RegistrationService;
import services.RideService;
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
    
    @Inject
    private RideService rideService;
    
    @Inject
    private InvoiceCalculationService ics;

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
        if (!isOverheid(securityContext)) {
            return Response.status(Status.UNAUTHORIZED).build();
        }

        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        User u = this.getUserFromToken(token);
        if (u != null) {
            return Response.accepted(new DTO_User(u)).build();
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
    @Path("rekeningrijders/{id}")
    public Response getRekeningrijderById(
            @PathParam("id") long id) {
        Rekeningrijder r = registrationService.findRekeningrijderById(id);
        if (r != null) {
            return Response.accepted(new DTO_Rekeningrijder(r)).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("rekeningrijders/username/{username}")
    public Response getRekeningrijderByUsername(
            @PathParam("username") String username) {
        List<Rekeningrijder> rekeningrijders = registrationService.findRekeningrijderByUsername(username);
        if (rekeningrijders != null) {
            List<DTO_Rekeningrijder> dtoRekeningrijders = new ArrayList<>();
            for (Rekeningrijder r : rekeningrijders) {
                dtoRekeningrijders.add(new DTO_Rekeningrijder(r));
            }
            return Response.accepted(dtoRekeningrijders).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("cartrackers")
    public Response getCartrackers() {

        List<Cartracker> cartrackers = registrationService.findAllCartrackers();
        if (cartrackers != null) {

            List<DTO_Cartracker> dtoCartrackers = new ArrayList<>();
            for (Cartracker c : cartrackers) {
                dtoCartrackers.add(new DTO_Cartracker(c));
            }

            return Response.accepted(dtoCartrackers).build();
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
            return Response.accepted(new DTO_Cartracker(cartracker)).build();
        }
        return Response.status(Status.BAD_REQUEST).build();

    }

    @GET
    @Path("cartrackers/{id}")
    public Response getCartrackerById(@PathParam("id") long id) {
        Cartracker c = registrationService.findCartrackerById(id);

        if (c != null) {
            return Response.accepted(new DTO_Cartracker(c)).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("cartrackers/hardware/{hardware}")
    public Response getCartrackersByHardware(
            @PathParam("hardware") String hardware) {
        List<Cartracker> cartrackers = registrationService.findCartrackersByHardware(hardware);
        if (cartrackers != null) {
            List<DTO_Cartracker> dtoCatrackers = new ArrayList<>();
            for (Cartracker c : cartrackers) {
                dtoCatrackers.add(new DTO_Cartracker(c));
            }
            return Response.accepted(dtoCatrackers).build();
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
            return Response.accepted(new DTO_Cartracker(c)).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Path("kmrates")
    public Response getKMRates() {
        System.out.println("1");
        List<KMRate> kmRates = invoiceService.findAllKMRates();
        System.out.println("2");
        if (kmRates != null) {
            System.out.println("3");

            List<DTO_KMRate> rates = new ArrayList<DTO_KMRate>();
            for (KMRate k : kmRates) {
                rates.add(new DTO_KMRate(k));
            }
            return Response.accepted(rates).build();
        }
        return Response.status(Status.FORBIDDEN).build();
    }

    @POST
    @Path("kmrates/new")
    public Response addKMRate(
            @Context HttpHeaders headers,
            @FormParam("region") String region,
            @FormParam("vehicleType") VehicleType vehicleType,
            @FormParam("rate") double rate) {

        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        User u = this.getUserFromToken(token);
        if(!u.isKm_prijs() || u == null) {
            return Response.status(Status.UNAUTHORIZED).build();
        }       

        KMRate k = new KMRate(region);
        k.addRatePerVehicleType(vehicleType, rate);

        try {
            invoiceService.addKMRate(k);
            return Response.accepted(k).build();
        } catch (Exception e) {
            return Response.status(Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Produces(APPLICATION_JSON)
    @Path("kmrates/{region}/{vehicleType}")
    public Response getKMRateByRegion(
            @PathParam("region") String region,
            @PathParam("vehicleType") VehicleType vehicleType,
            @FormParam("rate") double rate) {
        try {
            KMRate kmrate = invoiceService.findKMRateByRegion(region);
            Map<VehicleType, Double> rates = kmrate.getRatePerVehicleType();
            if (rates.containsKey(vehicleType)) {
                rates.remove(vehicleType);
            }
            rates.put(vehicleType, rate);
            kmrate.setRatePerVehicleType(rates);

            invoiceService.updateKMRate(kmrate);
            return Response.accepted(kmrate).build();

        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
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
            //return Response.accepted(invoices).build();
            List<DTO_Invoice> invoiceDTO = new ArrayList<>();
            for (Invoice i : invoices) {
                invoiceDTO.add(new DTO_Invoice(i));
            }
            return Response.accepted(invoiceDTO).build();
        }
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

//    @POST
//    @Path("invoices/{rekeningrijderId}/{vehicleId}/{year}/{month}/")
//    public Response reCalculateInvoice(){
//        
//    }
    
    @GET
    @Produces(APPLICATION_JSON)
    @Path("invoices/{rekeningrijderId}/{vehicleId}/{year}/{month}")
    public Response calculateInvoice(
            @PathParam("rekengingrijderId") long rekeningrijderId,
            @PathParam("vehicleId") long vehicleId,
            @PathParam("year") int year,
            @PathParam("month") int month) {
        List<Ride> rides = rideService.getRides(vehicleId, month, year);
        Rekeningrijder rekeningrijder = registrationService.findRekeningrijderById(rekeningrijderId);
        
        VehicleType type = registrationService.findVehicleById(vehicleId).getVehicleType();
        Invoice i = ics.calculateInvoice(vehicleId, month, year, rekeningrijder, rides, type);
        System.out.println("invoice ending: " + i);
        System.out.println("(0)-(0)");
        return Response.accepted(new DTO_Invoice(i)).build();
    }

    @GET
    @Path("invoices/{id}")
    public Response getInvoiceById(
            @PathParam("id") long id) {
        Invoice i = invoiceService.findInvoiceById(id);
        if (i != null) {
            return Response.accepted(new DTO_Invoice(i)).build();
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
            List<DTO_Invoice> dtoInvoices = toDTOInvoiceList(invoices);
            return Response.accepted(dtoInvoices).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Path("invoices/invoicestatus/{status}")
    public Response getInvoicesByStatus(
            @PathParam("status") InvoiceStatus status) {
        List<Invoice> invoices = invoiceService.findInvoicesByStatus(status);
        if (invoices != null) {
            List<DTO_Invoice> dtoInvoices = toDTOInvoiceList(invoices);
            return Response.accepted(dtoInvoices).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Path("vehicles")
    public Response getVehicles() {
        List<Vehicle> vehicles = registrationService.findAllVehicles();
        List<DTO_Vehicle> vehiclesDTO = new ArrayList<>();
        if (vehicles != null) {
            for (Vehicle v : vehicles) {
                vehiclesDTO.add(new DTO_Vehicle(v));
            }
            return Response.accepted(vehiclesDTO).build();
        }
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }

    @GET
    @Path("vehicles/{id}")
    public Response getVehicleById(
            @PathParam("id") long id) {
        Vehicle v = registrationService.findVehicleById(id);
        if (v != null) {
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
            @FormParam("cartrackerId") long cartrackerId) {
        Vehicle v = registrationService.findVehicleById(id);
        Cartracker c = registrationService.findCartrackerById(cartrackerId);
        if (v != null) {
            if (licensePlate != null) {
                v.setLicensePlate(licensePlate);
            }
            if (vehicleType != null) {
                v.setVehicleType(vehicleType);
            }

            if (c != null) {
                v.setCartracker(c);
            }
            registrationService.updateVehicle(v);
            return Response.accepted(new DTO_Vehicle(v)).build();
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

    private boolean isOverheid(SecurityContext context) {
        System.out.println(context.isUserInRole("OVERHEID"));
        if (context.isUserInRole("OVERHEID")) {
            return true;
        }
        return false;
    }

    private List<DTO_Invoice> toDTOInvoiceList(List<Invoice> invoices) {
        List<DTO_Invoice> dtoInvoices = new ArrayList<>();
        for (Invoice i : invoices) {
            dtoInvoices.add(new DTO_Invoice(i));
        }
        return dtoInvoices;
    }
}
