package com.example.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@Path("/login")
public class LoginController {

    @GET
    public Response index() {
        return Response.ok("login.jsp").build();
    }

    @POST
    public Response login(@FormParam("username") String username, 
                          @FormParam("password") String password, 
                          @FormParam("rememberMe") boolean rememberMe, 
                          @Context SecurityContext securityContext) {
        List<String> errors = new ArrayList<>();

        if (username == null || username.isEmpty()) {
            errors.add("Username cannot be empty.");
        }
        if (password == null || password.isEmpty()) {
            errors.add("Password cannot be empty.");
        }

        if (!errors.isEmpty()) {
            return Response.ok("login.jsp").entity(errors).build();
        }

        Credential credential = new Credential(username, password);
        boolean authenticated = securityContext.getUserPrincipal() != null && securityContext.getUserPrincipal().getName().equals(username);

        if (authenticated) {
            return Response.seeOther("/dashboard").build();
        } else {
            errors.add("Invalid username or password.");
            return Response.ok("login.jsp").entity(errors).build();
        }
    }
}