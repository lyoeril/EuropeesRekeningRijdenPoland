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
import domain.User;
import domain.UserGroup;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import services.UserService;

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

    @Inject
    private UserService userService;

    @GET
    public Response getRekeningrijder() {
        return Response.accepted("Welcome to REGISTER API").build();
    }

    @POST
    @Path("rekeningrijder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("email") String email,
            @FormParam("address") String address) {
        Rekeningrijder r = new Rekeningrijder(username, password, address, email);
        UserGroup group = registrationService.findByName("REKENINGRIJDER");
        System.out.println("group: " + group.getGroupName());
        r.addGroup(group);
        registrationService.addRekeningrijder(r);
        return Response.ok(r).build();
    }

    @POST
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("email") String email) {
        User u = new User(username, password, email);
        UserGroup group = registrationService.findByName("REKENINGRIJDER");
        System.out.println("group: " + group.getGroupName());
        u.addGroup(group);
        boolean finished = userService.register(u);
        if (finished) {
            return Response.ok(u).build();
        }
        return Response.status(Status.FORBIDDEN).build();
    }

}
