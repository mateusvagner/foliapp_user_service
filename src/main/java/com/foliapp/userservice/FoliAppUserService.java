package com.foliapp.userservice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.Claims;

import com.foliapp.userservice.domain.User;
import com.foliapp.userservice.exception.UserAlreadyExistsException;
import com.foliapp.userservice.fakes.UserDatabase;
import com.foliapp.userservice.resource.UserResource;

import io.smallrye.jwt.build.Jwt;

@Path("/user")
public class FoliAppUserService {

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResource postNewUser(UserResource newUser) {

        if (UserDatabase.getInstance().users.isEmpty()) {
            saveUser(newUser);
            return newUser;
        }

        for (User userData : UserDatabase.getInstance().users) {
            if (newUser.getEmail().equals(userData.getEmail())) {
                throw new UserAlreadyExistsException(newUser.getEmail());
            }
        }

        saveUser(newUser);
        return newUser;
    }

    private void saveUser(UserResource newUser) {
        User user = new User();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setAdress("New Adress");

        List<String> roles = new ArrayList<>();
        roles.add("User");
        roles.add("Admin");
        user.setRoles(roles);

        UserDatabase.getInstance().users.add(user);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAccessToken(@FormParam("email") String email, @FormParam("password") String password) {
        User user = validateUser(email, password);

        if (user == null) {
            throw new WebApplicationException("User or password not found", Response.Status.NOT_FOUND);
        }

        return Jwt.issuer("http://localhost:8080")
                .upn(user.getEmail())
                .groups(new HashSet<>(user.getRoles()))
                .claim(Claims.full_name, user.getName())
                .sign();
    }

    private User validateUser(String email, String password) {
        for (User userData : UserDatabase.getInstance().users) {
            if (email.equals(userData.getEmail()) && password.equals(userData.getPassword())) {
                return userData;
            }
        }
        return null;
    }
}