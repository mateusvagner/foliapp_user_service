package com.foliapp.userservice.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserAlreadyExistsException extends WebApplicationException {

    public UserAlreadyExistsException(String email) {
        super("The e-mail " + email + " already exists.",
                Response.Status.CONFLICT);
    }
}
