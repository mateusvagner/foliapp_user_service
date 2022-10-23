package com.foliapp.userservice;

import java.util.HashSet;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.Claims;

import com.foliapp.userservice.exception.UserAlreadyExistsByEmailException;
import com.foliapp.userservice.interfaceAdapter.controller.UserController;
import com.foliapp.userservice.web.resource.UserResource;

import io.smallrye.jwt.build.Jwt;

@Path("/user")
public class FoliAppUserService {

    @Inject
    UserController userController;

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResource postNewUser(UserResource newUser) {
        try {
            return userController.saveUser(newUser);
        } catch (UserAlreadyExistsByEmailException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        } catch (RuntimeException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAccessToken(@FormParam("email") String email, @FormParam("password") String password) {
        try {
            UserResource user = userController.logInUser(email, password);

            return Jwt.issuer("http://localhost:8080")
                    .upn(user.getEmail())
                    .groups(new HashSet<>(user.getRoles()))
                    .claim(Claims.full_name, user.getName())
                    .sign();

        } catch (RuntimeException e) {
            // TODO dividir exceptions
            throw new WebApplicationException("User or password not found", Response.Status.NOT_FOUND);
        }

    }

}