/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.Rekeningrijder;
import domain.Vehicle;
import dto.DTO_Rekeningrijder;
import dto.DTO_Vehicle;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import services.InvoiceService;
import services.RegistrationService;
import services.RideService;

/**
 *
 * @author Laurent
 */
@Path("politie")
@Produces(APPLICATION_JSON)
@JWTTokenNeeded
@Stateless
public class PolitieAPI {

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private RegistrationService registrationService;

    @GET
    public Response getPolitieUser(
            @Context SecurityContext securityContext) {
        if (!isPolitie(securityContext)) {
            return Response.status(Status.UNAUTHORIZED).build();
        }
        return Response.accepted().build();
    }

    @GET
    @Path("vehicles")
    public Response getVehicles(
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("2") @QueryParam("size") int size) {

        List<Vehicle> vehicles = registrationService.findAllVehicles();
        if (vehicles != null) {
            List<DTO_Vehicle> dtoVehicles = new ArrayList<>();
            for (Vehicle v : vehicles) {
                dtoVehicles.add(new DTO_Vehicle(v));
            }
            if ((offset + size) > dtoVehicles.size()) {
                size = dtoVehicles.size() - offset;
            }
            final List<DTO_Vehicle> result = dtoVehicles.subList(offset, offset + size);
            return Response.accepted(result).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("vehicles/{id}")
    public Response getVehicle(
            @PathParam("id") long id) {
        Vehicle v = registrationService.findVehicleById(id);
        if (v != null) {
            return Response.accepted(new DTO_Vehicle(v)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("vehicles/licenseplate/{licenseplate}")
    public Response getVehicleByLicenseplate(
            @PathParam("licenseplate") String licenseplate) {
        Vehicle v = registrationService.findVehicleByLicenseplate(licenseplate);
        if (v != null) {
            return Response.accepted(new DTO_Vehicle(v)).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Path("vehicles/{id}/owners")
    public Response getOwners(@PathParam("id") long id) {
        Vehicle v = registrationService.findVehicleById(id);
        if (v != null) {
            if (v.getOwnersHistory().size() > 0) {
                List<DTO_Rekeningrijder> rekeningrijders = new ArrayList<>();
                for (Rekeningrijder r : v.getOwnersHistory()) {
                    rekeningrijders.add(new DTO_Rekeningrijder(r));
                }
            }
            return Response.status(Status.NOT_FOUND).build();

        }
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

    @GET
    @Path("vehicle/{id}/history")
    public Response getRideHistory() {
//        List<Ride> history = invoiceService.find
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

    @GET
    @Path("ridestest")
    public Response getRideTest() {
        RideService rs = new RideService();
        rs.getRides(1, 10, 2018);
        return Response.accepted().build();
    }

    private boolean isPolitie(SecurityContext context) {
        System.out.println(context.isUserInRole("POLITIE"));
        if (context.isUserInRole("POLITIE")) {
            return true;
        }
        return false;
    }

}
