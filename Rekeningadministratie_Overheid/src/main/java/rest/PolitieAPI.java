/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import services.InvoiceService;
import services.RegistrationService;

/**
 *
 * @author Laurent
 */
@Path("politie")
@Stateless
public class PolitieAPI {

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private RegistrationService registrationService;

    @GET
    @Path("cars/{id}")
    public Response getCar() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("cars/{id}/owners")
    public Response getOwners() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
