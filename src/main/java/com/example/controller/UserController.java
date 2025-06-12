package com.example.controller;

import jakarta.mvc.Controller;
import jakarta.mvc.GET;
import jakarta.mvc.PathParam;
import jakarta.mvc.View;
import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.constraints.Pattern;

@RequestScoped
@Controller
public class UserController {

    @Inject
    private Account account;

    @GET
    @Path("/user/{username}")
    @View("registration_thanks.jsp")
    public String getUser(@PathParam("username") @Pattern(regexp = "^[a-zA-Z0-9_]{3,15}$") String username) {
        if (username == null || username.isEmpty()) {
            return "error.jsp";
        }
        account.setUsername(username);
        return "registration_thanks.jsp";
    }
}