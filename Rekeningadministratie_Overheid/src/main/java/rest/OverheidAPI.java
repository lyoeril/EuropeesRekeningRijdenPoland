///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package rest;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import domain.Rekeningrijder;
//import domain.User;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.HttpHeaders;
//import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//import services.InvoiceService;
//import services.RegistrationService;
//import services.UserService;
//
///**
// *
// * @author Laurent
// */
//@Path("overheid")
//@JWTTokenNeeded
//@Stateless
//public class OverheidAPI {
//
//    @Inject
//    private InvoiceService invoiceService;
//
//    @Inject
//    private RegistrationService registrationService;
//
//    @Inject
//    private UserService userService;
//
//    @GET
//    @Produces(APPLICATION_JSON)
//    public Response getEmployee(@Context HttpHeaders headers) {
//        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
//        User u = this.getUserFromToken(token);
//        if(u != null){
//            return Response.accepted(u).build();
//        }
//        return Response.noContent().build();
//    }
//    
//    @Path("cartrackers")
//    public Response getCartrackers(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    @Path("cartrackers/{id}")
//    public Response getCartrackerById(@PathParam("id") long id){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    @Path("cartrackers/{id}")
//    public Response setCartracker(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    @Path("KMRates")
//    public Response getKMRates(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    @Path("KMRates/{region}")
//    public Response getKMRateByRegion(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    @Path("KMRates/{region}")
//    public Response setKMRateByRegion(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    @Path("invoices")
//    public Response getCalculatedInvoices(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    @Path("invoices/vehicle/{year}/{month}")
//    public Response reCalculateInvoice(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    @Path("invoices/cartracker/{id}/{year}/{month}")
//    public Response getInvoicesByCartracker(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    @Path("invoices/rekeningrijder/{id}")
//    public Response getInvoicesByRekeningrijder(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    @Path("invoices/invoicestatus/{status}")
//    public Response getInvoicesByStatus(){
//        return Response.status(Status.NOT_IMPLEMENTED).build();
//    }
//    
//    
//
//    private String getUsernameFromToken(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC512("supersecret");
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer("mario")
//                    .build();
//            DecodedJWT jwt = verifier.verify(token);
//            final String username = jwt.getSubject();
//            return username;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private Rekeningrijder getRekeningrijderFromUsername(String username) {
//        User u = userService.findByUsername(username).get(0);
//
//        if (u == null) {
//            return null;
//        }
//        Rekeningrijder rekeningrijder = registrationService.findRekeningrijderById(u.getId());
//        return rekeningrijder;
//    }
//
//    private User getUserFromUsername(String username) {
//        User u = userService.findByUsername(username).get(0);
//        return u;
//    }
//
//    private User getUserFromToken(String token) {
//        String username = this.getUsernameFromToken(token);
//        return this.getUserFromUsername(username);
//    }
//
//    private Rekeningrijder getRekeningrijderFromToken(String token) {
//        String username = this.getUsernameFromToken(token);
//        return this.getRekeningrijderFromUsername(username);
//    }
//}
