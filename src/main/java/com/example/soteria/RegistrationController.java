import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationData", new RegistrationData());
        return "registration";
    }

    @PostMapping
    public String registerUser(RegistrationData registrationData, RedirectAttributes redirectAttributes) {
        if (!userService.validateRegistrationData(registrationData)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Validation failed. Please check your input.");
            return "redirect:/registration";
        }
        if (userService.isUsernameOrEmailTaken(registrationData.getUsername(), registrationData.getEmail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Username or email already taken.");
            return "redirect:/registration";
        }
        try {
            userService.createUserAccount(registrationData);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed. Please try again.");
            return "redirect:/registration";
        }
        return "redirect:/confirmation";
    }
}