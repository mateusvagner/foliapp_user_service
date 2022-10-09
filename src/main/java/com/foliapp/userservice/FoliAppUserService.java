package com.foliapp.userservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.foliapp.userservice.domain.User;
import com.foliapp.userservice.exception.UserAlreadyExistsException;
import com.foliapp.userservice.fakes.UserDatabase;
import com.foliapp.userservice.resource.NewUserResource;

@Path("/user")
public class FoliAppUserService {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public NewUserResource postNewUser(NewUserResource newUser) {

        if (UserDatabase.getInstance().users.isEmpty()) {
            saveUser(newUser);
            return newUser;
        }

        for (User userData : UserDatabase.getInstance().users) {
            if (newUser.getEmail().equals(userData.getEmail())) {
                throw new UserAlreadyExistsException(newUser.getEmail());
            } else {
                saveUser(newUser);
                return newUser;
            }
        }
        return null;
    }

    private void saveUser(NewUserResource newUser) {
        User user = new User();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setAdress("New Adress");

        UserDatabase.getInstance().users.add(user);
    }
}