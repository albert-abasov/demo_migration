package com.example.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.registration.User;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationValidator registrationValidator;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration.html";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
        registrationValidator.validate(user, model);
        if (model.containsAttribute("errors")) {
            return "registration.html";
        }
        registrationService.createUser(user);
        redirectAttributes.addFlashAttribute("message", "Registration successful!");
        return "redirect:/thank-you";
    }
}