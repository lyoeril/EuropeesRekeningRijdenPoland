//package rest;
//
//import domain.User;
//import java.util.List;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.SecurityContext;
//import services.UserService;
//
//@Path("user")
//@Produces(APPLICATION_JSON)
//@Consumes(APPLICATION_JSON)
//@Stateless
//@JWTTokenNeeded
//public class UserAPI{
//
//    @Inject
//    private UserService us;
//
//    @Context
//    SecurityContext securityContext;
//
//    @GET
//    public Response get() {
//        String username = securityContext.getUserPrincipal().getName();
//        List<User> users = us.findByUsername(username);
//        if (users != null && users.size() == 1) {
//            User u = users.get(0);
//            System.out.print("bij GET PRIMA");
//            return Response.ok(u).build();
//        }
//        System.out.print("bij GET");
//        return Response.serverError().build();
//    }
//}