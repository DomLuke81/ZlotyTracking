package pl.domluke.zlotytracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.domluke.zlotytracking.domain.LoggedUser;
import pl.domluke.zlotytracking.domain.User;
import pl.domluke.zlotytracking.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/user/loginForm";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/registerForm";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult, Model model) {
        if (userService.findUserByName(user.getName()) != null) {
            bindingResult.rejectValue("name", "error.user",
                    "Użytkownik o takiej nazwie już jest zarejestrowany w serwisie");
        }
        if (bindingResult.hasErrors()) {
            return "user/registerForm";
        }
        userService.saveUser(user);
        model.addAttribute("registered", "true");
        return "user/registerForm";
    }

    @GetMapping("/redirect")
    public String redirect(@AuthenticationPrincipal LoggedUser loggedUser) {
        if (loggedUser.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "redirect:/admin";
        } else {
            return "redirect:/user";
        }
    }
}
