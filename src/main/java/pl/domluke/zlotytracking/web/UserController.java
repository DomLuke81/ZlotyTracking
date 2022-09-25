package pl.domluke.zlotytracking.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.domluke.zlotytracking.domain.LoggedUser;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String userHomePage(Model model, @AuthenticationPrincipal LoggedUser loggedUser) {
        model.addAttribute("user", loggedUser.getUser());
        return "user/home";
    }

}
