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
import domain.Invoice;
import domain.Rekeningrijder;
import domain.User;
import domain.Vehicle;
import dto.DTO_Invoice;
import dto.DTO_Rekeningrijder;
import dto.DTO_Vehicle;
import enums.InvoiceStatus;
import enums.VehicleType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
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
import services.InvoiceService;
import services.RegistrationService;
import services.UserService;

/**
 *
 * @author Laurent
 */
@Path("rekeningrijder")
@JWTTokenNeeded
@Stateless
public class RekeningrijderAPI {

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private RegistrationService registrationService;

    @Inject
    private UserService userService;

    //TODO
    //Still need to fix User/Kwetteraar difference; Essential to just need 1 databasecall
    //TODO
    //FIX DATABASECALL for findByUsername();
    @GET
    @Produces(APPLICATION_JSON)
    public Response getRekeningrijder(@Context HttpHeaders headers) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();

        System.out.println("TOKIETOKIE: " + token);
        String username = this.getUsernameFromToken(token);
        if (username != null) {
            User u = userService.findByUsername(username).get(0);
            Rekeningrijder r = registrationService.findRekeningrijderById(u.getId());

            DTO_Rekeningrijder dto = new DTO_Rekeningrijder(r);
            return Response.accepted(dto).build();
        }

        return Response.accepted(token).build();
    }

    @PUT
    @Path("update")
    @Produces(APPLICATION_JSON)
    public Response updateRekeningrijder(
            @Context HttpHeaders headers,
            @FormParam("email") String email,
            @FormParam("address") String address) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder rekeningrijder = this.getRekeningrijderFromToken(token);

        if (rekeningrijder != null) {
            rekeningrijder.setEmail(email);
            rekeningrijder.setAddress(address);
            registrationService.updateRekeningrijder(rekeningrijder);
            return Response.accepted(new DTO_Rekeningrijder(rekeningrijder)).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Path("invoices/new")
    public Response newInvoice(@Context HttpHeaders headers) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder rekeningrijder = this.getRekeningrijderFromToken(token);

        Invoice i = new Invoice(1, 20.00, new GregorianCalendar(TimeZone.getTimeZone("434")), rekeningrijder);
        rekeningrijder.getInvoices().add(i);
        registrationService.updateRekeningrijder(rekeningrijder);
        invoiceService.addInvoice(i);
        return Response.accepted().build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("invoices")
    public Response getInvoices(@Context HttpHeaders headers) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder rekeningrijder = this.getRekeningrijderFromToken(token);

        List<Invoice> invoices = rekeningrijder.getInvoices();
        if (invoices != null) {
            List<DTO_Invoice> dto_invoices = new ArrayList<>();
            for(Invoice i : invoices){
                dto_invoices.add(new DTO_Invoice(i));
            }            
            
            return Response.accepted(dto_invoices).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("invoices/{id}")
    public Response getInvoiceById(@PathParam("id") long id) {
        try {
            Invoice i = invoiceService.findInvoiceById(id);
            if (i != null) {
                return Response.ok(i).build();
            }
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("invoices/{year}/{month}")
    public Response getInvoicebyDate(
            @Context HttpHeaders headers,
            @PathParam("year") int year,
            @PathParam("month") int month) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder rekeningrijder = this.getRekeningrijderFromToken(token);

        List<Invoice> invoices = rekeningrijder.getInvoices();
        if (invoices != null) {
            for (Invoice i : invoices) {
                Calendar c = i.getDate();
                System.out.println("c.MONTh: " + c.get(Calendar.MONTH));
                System.out.println("DATE: " + c.get(Calendar.YEAR) + " " + c.get(Calendar.MONTH) + " " + c.get(Calendar.DATE));
                if (c.get(Calendar.YEAR) == year && (c.get(Calendar.MONTH) + 1) == month) {
                    return Response.accepted(new DTO_Invoice(i)).build();
                }
            }
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    //TODO
    @PUT
    @Path("invoices/{year}/{month}/")
    public Response payInvoice(
            @Context HttpHeaders headers,
            @PathParam("year") int year,
            @PathParam("month") int month,
            @PathParam("id") long id) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder r = this.getRekeningrijderFromToken(token);
        Calendar date = new GregorianCalendar(year, month, 1);
        Invoice toReturn = invoiceService.findInvoiceById(id);
        if (toReturn != null) {
            toReturn.setStatus(InvoiceStatus.PAID);
            invoiceService.updateInvoice(toReturn);
            return Response.accepted().build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/cars")
    public Response getCars(@Context HttpHeaders headers) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();

        Rekeningrijder r = this.getRekeningrijderFromToken(token);

        if (r != null) {
            List<Vehicle> vehicles = r.getOwnedVehicles();
            List<DTO_Vehicle> vehicleNames = new ArrayList<>();
            for(Vehicle v: vehicles){
                DTO_Vehicle vehicle = new DTO_Vehicle(v);
                vehicleNames.add(vehicle);
            }
            return Response.ok(vehicleNames).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/cars/{carId}")
    public Response getCarById(
            @Context HttpHeaders headers,
            @PathParam("carId") long carId) {
        if (carId != 0L) {
            try {
                String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
                System.out.println("TOKIETOKIE: " + token);
                String username = this.getUsernameFromToken(token);

                List<Vehicle> vehicles = this.getRekeningrijderFromUsername(username).getOwnedVehicles();

                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getId() == carId) {
                        return Response.ok(vehicle).build();
                    }
                }
            } catch (Exception e) {
                return Response.status(Status.NOT_FOUND).build();
            }
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Path("cars/newcar")
    public Response newCar(
            @Context HttpHeaders headers,
            @FormParam("vehicletype") VehicleType vehicleType,
            @FormParam("licensePlate") String licensePlate) {

        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder r = this.getRekeningrijderFromToken(token);
        Vehicle v = new Vehicle(vehicleType, licensePlate);
        v.getOwnersHistory().add(r);
        r.getOwnedVehicles().add(v);
        registrationService.updateRekeningrijder(r);
        return Response.accepted().build();
    }
    
    @PUT
    @Produces(APPLICATION_JSON)
    @Path("cars/{carId}/update")
    public Response updateCar(
            @Context HttpHeaders headers,
            @FormParam("vehicletype") VehicleType vehicleType,
            @FormParam("licensePlate") String licensePlate) {

        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder r = this.getRekeningrijderFromToken(token);
        List<Vehicle> vehicles = r.getOwnedVehicles();
        Vehicle old = new Vehicle(vehicleType, licensePlate);
        for(Vehicle v: vehicles){
            if(v.getLicensePlate() == licensePlate){
                r.getOwnedVehicles().remove(v);
                old.setVehicleType(vehicleType);
                r.getOwnedVehicles().add(v);
                registrationService.updateRekeningrijder(r);
            }
        }        
        return Response.accepted().build();
    }

    @DELETE
    @Produces(APPLICATION_JSON)
    @Path("cars/{carId}")
    public Response deleteCarFromUser(
            @Context HttpHeaders headers,
            @PathParam("carId") long carId) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder r = this.getRekeningrijderFromToken(token);
        for (Vehicle v : r.getOwnedVehicles()) {
            if (v.getId() == carId) {
                r.getOwnedVehicles().remove(v);
                registrationService.updateRekeningrijder(r);
                return Response.accepted().build();
            }
        }
        return Response.status(Status.FORBIDDEN).build();
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

    private Rekeningrijder getRekeningrijderFromToken(String token) {
        String username = this.getUsernameFromToken(token);
        return this.getRekeningrijderFromUsername(username);
    }
}
