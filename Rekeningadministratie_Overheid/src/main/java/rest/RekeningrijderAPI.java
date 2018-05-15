/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod.*;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import services.InvoiceService;
import services.RegistrationService;

/**
 *
 * @author Laurent
 */
@Path("rekeningrijder")
@Stateless
public class RekeningrijderAPI {

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private RegistrationService registrationService;
    
    @PUT
    @Path("update")
    @Consumes(APPLICATION_JSON)
    public Response updateRekeningrijder(){
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("invoices")
    public Response getInvoices() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("invoices/{year}/{month}")
    public Response getInvoicebyDate() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("invoices/{year}/{month}/pay")
    public Response payInvoice() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("cars")
    public Response getCars() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("cars/{carId}")
    public Response getCar(){
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @PUT
    @Path("cars/{carId}")
    public Response updateCar(){
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
