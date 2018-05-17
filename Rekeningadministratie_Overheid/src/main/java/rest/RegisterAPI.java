/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import services.InvoiceService;
import services.RegistrationService;
import domain.Rekeningrijder;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Laurent
 */
@Path("/register")
@Stateless
public class RegisterAPI {

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private RegistrationService registrationService;

    @GET
    public Response getRekeningrijder() {
        return Response.accepted("Welcome to REGISTER API").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(
            @FormParam("username") String username,
            @FormParam("address") String address) {
        Rekeningrijder r = new Rekeningrijder("email", "username", "password", "test");
        registrationService.addRekeningrijder(r);
        return Response.ok(r).build();
    } 

}
