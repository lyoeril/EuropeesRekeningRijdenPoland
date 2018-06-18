/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.rest.api;

import com.poland.dto.entities.BasicLocationDTO;
import com.poland.dto.entities.DTOConverter;
import com.poland.dto.entities.PoliceVehicleDTO;
import com.poland.entities.Location;
import com.poland.entities.Vehicle;
import com.poland.service.RegistrationService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
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
    public Response reportStolenVehicle(String currentLocation, @PathParam("uuid") String uuid) {

        Location l = null;
        if (uuid != null && !uuid.equals("")) {
            try {
                JSONObject jsonObject = new JSONObject(currentLocation);
                double lat = jsonObject.getJSONObject("currentLocation").getDouble("lat");
                double lon = jsonObject.getJSONObject("currentLocation").getDouble("lon");

                l = new Location(new Date(), lat, lon, null);
            } catch (JSONException ex) {
            }
            this.registrationService.reportStolenVehicle(uuid, l);
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/vehicle/{uuid}/location")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLocationStolenVehicle(@PathParam("uuid") String uuid) {
        if (uuid != null && !uuid.equals("")) {
            Vehicle v = registrationService.getVehicleService().getVehicleByAuthorisationCode(uuid);
            if (v == null || !v.isStolen()) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            PoliceVehicleDTO vehicleTarget = new PoliceVehicleDTO();
            vehicleTarget.fromVehicleLocation(v.getAuthorisationCode(), v.getLocation());
            return Response.ok().entity(vehicleTarget).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/stolenVehicles")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllStolenVehicles() {
        List<Vehicle> vehicles = registrationService.getVehicleService().getVehiclesIfStolen();

        List<PoliceVehicleDTO> policeVehicleTargetList = new ArrayList<>();
        DTOConverter.toPoliceVehicleDTOList(vehicles, policeVehicleTargetList);
        return Response.ok().entity(policeVehicleTargetList).build();
    }
}
