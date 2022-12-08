package com.foliapp.userservice;

import java.util.HashSet;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.foliapp.userservice.exception.LoginFailedException;
import com.foliapp.userservice.exception.NameLengthNotValidException;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
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
    @Retry(delay = 2000)
    @Timeout(7000)
    public UserResource postNewUser(UserResource newUser) {
        try {
            newUser.setKeyIdentifier(UUID.randomUUID().toString());
            return userController.saveUser(newUser);
        } catch (UserAlreadyExistsByEmailException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        } catch (NameLengthNotValidException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_ACCEPTABLE);
        } catch (RuntimeException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(delay = 2000)
    @Timeout(7000)
    public String getAccessToken(@FormParam("email") String email, @FormParam("password") String password) {
        try {
            UserResource user = userController.logInUser(email, password);

            return Jwt.issuer("http://localhost:8082")
                    .upn(user.getEmail())
                    .groups(new HashSet<>(user.getRoles()))
                    .claim(Claims.full_name, user.getName())
                    .claim(Claims.kid, user.getKeyIdentifier())
                    .sign();

        } catch (LoginFailedException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.UNAUTHORIZED);
        }
    }

}