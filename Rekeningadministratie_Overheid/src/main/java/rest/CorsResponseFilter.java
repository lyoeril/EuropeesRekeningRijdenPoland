/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Laurent
 */

@Provider
public class CorsResponseFilter implements ContainerResponseFilter{
    
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        
        System.out.println("URL " + requestContext.getUriInfo().getBaseUri());
        
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "http://192.168.25.33");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "Origin, Access-Control-Allow-Origin, Content-Type, Accept, Authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "POST, GET, PUT, DELETE");
    }
}
