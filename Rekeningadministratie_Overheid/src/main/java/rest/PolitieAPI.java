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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import services.InvoiceService;
import services.RegistrationService;

/**
 *
 * @author Laurent
 */
@Path("politie")
@Produces(APPLICATION_JSON)
@Stateless
public class PolitieAPI {

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private RegistrationService registrationService;

    @GET
    @Path("vehicles")
    public Response getVehicles() {
        List<Vehicle> vehicles = registrationService.findAllVehicles();
        if (vehicles != null) {
            List<DTO_Vehicle> dtoVehicles = new ArrayList<>();
            for (Vehicle v : vehicles) {
                dtoVehicles.add(new DTO_Vehicle(v));
            }
            return Response.accepted(dtoVehicles).build();
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
    @Path("vehicle/{id}/owners")
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
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

}
