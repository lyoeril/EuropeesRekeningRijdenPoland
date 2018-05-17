/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.Invoice;
import domain.Rekeningrijder;
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
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
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
    
    @GET
    public Response getRekeningrijder(){
        return Response.accepted("test").build();
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
            @FormParam("address") String address){
        
        if(id == 0){
            return Response.status(Status.NOT_FOUND).build();
        }
        
        Rekeningrijder rekeningrijder = registrationService.findRekeningrijderById(id);
        if(rekeningrijder != null){
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
    public Response getInvoiceById(@PathParam("id") long id){
        try{
            Invoice i = invoiceService.findInvoiceById(id);
            if(i != null){
                return Response.ok(i).build();
            }
        } catch(Exception e){
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
        if(r != null){
            return Response.ok(r.getOwnedVehicles()).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/{id}/cars/{carId}")
    public Response getCar(@PathParam("carId") long carId,
                           @PathParam("rekeningrijderId") long rrId){
        if(carId != 0L && rrId != 0L){
            try{
                List<Vehicle> v = registrationService.findRekeningrijderById(rrId).getOwnedVehicles();
                for(Vehicle vehicle : v){
                    if(vehicle.getId() == carId){
                        return Response.ok(vehicle).build();
                    }
                }
            }
            catch(Exception e){
                return Response.status(Status.NOT_FOUND).build();
            }
        }
        return Response.status(Status.NOT_FOUND).build();
    }
    
    @PUT
    @Path("cars/{carId}")
    public Response updateCar(){
        return Response.status(Status.NOT_FOUND).build();
    }
}
