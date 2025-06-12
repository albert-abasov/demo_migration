package com.example.controller;

import jakarta.mvc.Controller;
import jakarta.mvc.View;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import com.example.service.UserService;
import com.example.util.ValidationUtils;

@Controller
@Path("/registration")
public class RegistrationController {

    @Inject
    private UserService userService;

    @GET
    @View("registration.jsp")
    public Response showRegistrationForm(@Context UriInfo uriInfo) {
        List<String> errors = new ArrayList<>();
        return Response.ok().entity(errors).build();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @View("registration.jsp")
    public Response registerUser(@Context HttpHeaders headers, MultivaluedMap<String, String> formParams) {
        String username = formParams.getFirst("username");
        String password = formParams.getFirst("password");
        String email = formParams.getFirst("email");
        List<String> errors = new ArrayList<>();

        boolean isValid = ValidationUtils.validateRegistrationInput(username, password, email, errors);

        if (!isValid) {
            return Response.ok().entity(errors).build();
        }

        try {
            userService.createAccount(username, password, email);
        } catch (Exception e) {
            errors.add("Error creating account: " + e.getMessage());
            return Response.ok().entity(errors).build();
        }

        return Response.seeOther(uriInfo.getAbsolutePathBuilder().path("success").build()).build();
    }
}