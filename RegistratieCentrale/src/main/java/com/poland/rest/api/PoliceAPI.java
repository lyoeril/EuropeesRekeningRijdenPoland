/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.rest.api;

import com.poland.dto.entities.BasicLocationDTO;
import com.poland.dto.entities.PoliceVehicleDTO;
import com.poland.entities.Location;
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
            Location location = new Location();
            location.setLatitude(30);
            location.setLongitude(-30);
            PoliceVehicleDTO vehicleTarget = new PoliceVehicleDTO();
            vehicleTarget.fromVehicleLocation(uuid, location);
            return Response.ok().entity(vehicleTarget).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
