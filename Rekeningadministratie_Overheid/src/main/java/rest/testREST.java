/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Laurent
 */

@Path("test")
public class testREST {
    
    @GET
    public String test(){
        return "test";
    }
}
