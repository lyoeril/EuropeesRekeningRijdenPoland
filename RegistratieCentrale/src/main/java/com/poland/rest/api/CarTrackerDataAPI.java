/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.rest.api;

import com.poland.dto.entities.DTOConverter;
import com.poland.dto.entities.RideDTO;
import com.poland.entities.Location;
import com.poland.entities.Ride;
import com.poland.entities.Vehicle;
import com.poland.service.RegistrationService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @GET
    @Path("/get/RideByCode/{authenticationCode}/{startDate}/{endDate}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLocationsByAuthenticationCode(@PathParam("authenticationCode") String authenticationCode, @PathParam("startDate") String start, @PathParam("endDate") String end) {
        List<Ride> rides = new ArrayList<>();

        if (authenticationCode != null && !authenticationCode.equals("") && start != null && end != null) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date startDate = sdf.parse(start);
                Date endDate = sdf.parse(end);

                rides = registrationService.getRideService().getRidesOnDate(authenticationCode, startDate, endDate);
            } catch (ParseException ex) {
                Logger.getLogger(CarTrackerDataAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

        List<RideDTO> rideTargetList = new ArrayList<>();
        DTOConverter.toRideDTOList(rides, rideTargetList);

        return Response.status(Response.Status.OK).entity(rideTargetList).build();
    }

    @GET
    @Path("/get/RidesByCode/{authenticationCode}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRidesByAuthenticationCode(@PathParam("authenticationCode") String authenticationCode) {
        List<Ride> rides = new ArrayList<>();

        if (authenticationCode != null && !authenticationCode.equals("")) {
            rides = registrationService.getRideService().getRidesByAuthenticationCode(authenticationCode);
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

        List<RideDTO> rideTargetList = new ArrayList<>();
        DTOConverter.toRideDTOList(rides, rideTargetList);

        return Response.status(Response.Status.OK).entity(rideTargetList).build();
    }
}
