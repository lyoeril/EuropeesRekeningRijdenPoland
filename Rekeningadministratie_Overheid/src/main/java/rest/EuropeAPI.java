/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

@Path("/europe")
public class EuropeAPI {

    @PUT
    @Path("/vehicle/{uuid}/calculatePricing")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerLocation(@PathParam("uuid") String uuid, JSONObject jsonObject) {
        if (uuid != null && !uuid.equals("")) {
//            JSONObject json = new JSONObject();
//            json.put("uuid", uuid);
//            json.put("tripTotalKM", 50.12);
//            json.put("tripPrice", 13.4);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
}
