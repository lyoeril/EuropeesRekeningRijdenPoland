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
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod.*;
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

    @GET
    @Produces(APPLICATION_JSON)
    public Response getRekeningrijder(@Context HttpHeaders headers) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();

        System.out.println("TOKIETOKIE: " + token);
        String username = this.getUsernameFromToken(token);
        if(username != null){
            User u = userService.findByUsername(username).get(0);
            Rekeningrijder r = registrationService.findRekeningrijderById(u.getId());
            return Response.accepted(r).build();
        }

        return Response.accepted(token).build();
    }

//    @GET
//    public String test(){
//        return "test";
//    }
    @PUT
    @Path("update")
    @Consumes(APPLICATION_JSON)
    public Response updateRekeningrijder(
            @FormParam("id") long id,
            @FormParam("username") String username,
            @FormParam("email") String email,
            @FormParam("address") String address) {

        if (id == 0) {
            return Response.status(Status.NOT_FOUND).build();
        }

        Rekeningrijder rekeningrijder = registrationService.findRekeningrijderById(id);
        if (rekeningrijder != null) {
            rekeningrijder.setAddress(address);
            registrationService.updateRekeningrijder(rekeningrijder);
            return Response.accepted(rekeningrijder).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("invoices")
    public Response getInvoices() {
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("invoices/{id}")
    public Response getInvoiceById(@PathParam("id") long id) {
        try {
            Invoice i = invoiceService.findInvoiceById(id);
            if (i != null) {
                return Response.ok(i).build();
            }
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("invoices/{year}/{month}")
    public Response getInvoicebyDate(@PathParam("year") String year, @PathParam("month") String month) {
        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("invoices/{year}/{month}/pay")
    public Response payInvoice(@PathParam("year") String year, @PathParam("month") String month) {
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("{id}/cars")
    public Response getCars(@PathParam("id") long id) {
        Rekeningrijder r = registrationService.findRekeningrijderById(id);
        if (r != null) {
            return Response.ok(r.getOwnedVehicles()).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/cars/{carId}")
    public Response getCar(@PathParam("carId") long carId,
            @PathParam("rekeningrijderId") long rrId) {
        if (carId != 0L && rrId != 0L) {
            try {
                List<Vehicle> v = registrationService.findRekeningrijderById(rrId).getOwnedVehicles();
                for (Vehicle vehicle : v) {
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

    @PUT
    @Path("cars/{carId}")
    public Response updateCar() {
        return Response.status(Status.NOT_FOUND).build();
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
}
