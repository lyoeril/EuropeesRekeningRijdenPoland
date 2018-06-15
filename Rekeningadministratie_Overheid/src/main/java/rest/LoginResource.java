package rest;

import domain.User;
import domain.UserGroup;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import static javax.ws.rs.core.MediaType.*;
import javax.ws.rs.core.Response;
import services.LoginService;
import services.RegistrationService;
import services.UserService;

@Path("login")
@Produces(APPLICATION_JSON)
@Stateless
public class LoginResource {

    @Inject
    private LoginService ls;
    
    @Inject
    private UserService userService;

//    @POST
//    public Response login(User user) {
//
//        boolean valid = ls.authenticate(user.getUsername(), user.getPassword());
//
//        System.out.println("Username " + user.getUsername());
//        System.out.println("Password " + user.getPassword());
//
//        if (valid) {
//            String token = ls.issueToken(user.getUsername());
//            return Response.ok()
//                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
//                    .header("Access-Control-Expose-Headers", "Authorization")
//                    .build();
//        }
//
//        return Response.status(Response.Status.UNAUTHORIZED).build();
//    }
//}
    @POST
    @Produces(APPLICATION_JSON)
    public Response inloggen(@FormParam("username") String username, @FormParam("password") String password) {

        boolean valid = ls.authenticate(username, password);

        System.out.println("Username " + username);
        System.out.println("Password " + password);
        System.out.println("Valid: " + valid);

        if (valid) {
            List<User> u = userService.findByUsername(username);
            User user = u.get(0);
            
            HashSet<UserGroup> groups = user.getGroups();
            
            
            String token = ls.issueToken(username, groups);
            System.out.println("token " + token);
            return Response.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .header("Access-Control-Expose-Headers", "Authorization")
                    .build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
