/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Laurent
 */

@Path("/linkTracker")
@Stateless
public class simulatieAPI {
    
    @POST
    @Path("{trackerId}")
    public Response linkTracker(@PathParam("trackerId") String id) {
        System.out.printf("Checking TrackerId %s\n", id);
        return Response
                .status(Status.OK)
                .build();
    }
}
