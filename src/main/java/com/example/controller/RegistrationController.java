package com.example.controller;

import jakarta.mvc.Controller;
import jakarta.mvc.View;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import com.example.service.UserService;
import com.example.model.UserRegistrationForm;
import com.example.util.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

@Controller
@Path("/registration")
public class RegistrationController {

    @Inject
    private UserService userService;

    @GET
    @View("registration.jsp")
    public String showRegistrationForm() {
        return "registration.jsp";
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response registerUser(UserRegistrationForm form) {
        List<String> errorMessages = new ArrayList<>();

        if (ValidationUtils.isUsernameEmpty(form.getUsername()) || !ValidationUtils.isUsernameUnique(form.getUsername())) {
            errorMessages.add("Username must not be empty and must be unique.");
        }
        if (!ValidationUtils.isPasswordValid(form.getPassword())) {
            errorMessages.add("Password does not meet complexity requirements.");
        }
        if (!ValidationUtils.isEmailValid(form.getEmail()) || !ValidationUtils.isEmailUnique(form.getEmail())) {
            errorMessages.add("Email must be in a valid format and must be unique.");
        }

        if (!errorMessages.isEmpty()) {
            return Response.ok("registration.jsp").entity(errorMessages).build();
        }

        try {
            userService.createUser(form);
            return Response.seeOther(Response.seeOther("registration_thanks.jsp")).build();
        } catch (Exception e) {
            errorMessages.add("An error occurred while creating the account: " + e.getMessage());
            return Response.ok("registration.jsp").entity(errorMessages).build();
        }
    }
}