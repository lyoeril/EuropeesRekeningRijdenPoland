/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.rest.api;

import com.poland.entities.Location;
import com.poland.service.RegistrationService;
import java.util.Date;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author PC-YOERI
 */
@Path("/cartrackerdata")
public class CarTrackerDataAPI {

    private RegistrationService registrationService;

    @Inject
    public CarTrackerDataAPI(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @POST
    @Path("/post/update")
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerLocation(@FormParam("id") long id, @FormParam("latitude") Double latitude, @FormParam("longitude") Double longitude) {
        
        Location location = new Location(new Date(), latitude, longitude);
        location = registrationService.getLocationService().createLocation(location);
        return Response.status(Response.Status.CREATED).entity(location).build();
    }
}
