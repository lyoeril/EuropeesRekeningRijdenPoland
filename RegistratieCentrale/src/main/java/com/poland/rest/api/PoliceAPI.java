/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.rest.api;

import com.poland.service.RegistrationService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author PC-YOERI
 */
@Path("/police")
public class PoliceAPI {

    private RegistrationService registrationService;

    @Inject
    public PoliceAPI(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PUT
    @Path("/vehicle/{uuid}/reportStolen")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response reportStolenVehicle(@PathParam("uuid") String uuid, JSONObject jsonObject) {
        if (uuid != null && !uuid.equals("")) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/vehicle/{uuid}/location")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLocationStolenVehicle(@PathParam("uuid") String uuid) {
        if (uuid != null && !uuid.equals("")) {
            JSONObject json = new JSONObject();
            json.put("uuid", uuid);
            JSONObject location = new JSONObject();
            location.put("lat", 30);
            location.put("lon", 30);
            json.put("", location);
            return Response.ok().entity(json).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
