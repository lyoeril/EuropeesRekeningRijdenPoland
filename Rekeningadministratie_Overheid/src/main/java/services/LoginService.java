package services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dao.interfaces.IUserDAO;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import domain.User;
import domain.UserGroup;
import java.util.ArrayList;
import java.util.HashSet;

@Stateless
public class LoginService {

    @Inject
    private IUserDAO IUserDAO;

    public boolean authenticate(String username, String password) {
        List<User> users = IUserDAO.findByUsername(username);
        if (users.isEmpty()) {
            System.out.println("users empty");
            return false;
        }

        for (User u : users) {
            System.out.println("user " + u.getUsername());
        }

        User user = users.get(0);
        System.out.println("Loaded pw: " + password);
        System.out.println("Password door user: " + user.getPassword());

        return password.equals(user.getPassword());
    }

    public String issueToken(String login, HashSet<UserGroup> groups) {

        String[] test = new String[0];
        if (groups != null) {
            List<String> userGroupsString = new ArrayList<>();
            for (UserGroup group : groups) {
                userGroupsString.add(group.getGroupName());
            }

            test = new String[userGroupsString.size()];
            test = userGroupsString.toArray(test);
        }

        Algorithm algorithm;
        String token = "";
        try {
            algorithm = Algorithm.HMAC512("supersecret");
            token = JWT.create()
                    .withSubject(login)
                    .withIssuer("mario")
                    .withArrayClaim("groups", test)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
        }
        return token;
    }
}
