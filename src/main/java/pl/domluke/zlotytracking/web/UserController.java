package pl.domluke.zlotytracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.domluke.zlotytracking.domain.LoggedUser;
import pl.domluke.zlotytracking.domain.User;
import pl.domluke.zlotytracking.service.NoteSpotService;
import pl.domluke.zlotytracking.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final NoteSpotService noteSpotService;
    private final UserService userService;

    @Autowired
    public UserController(NoteSpotService noteSpotService, UserService userService) {
        this.noteSpotService = noteSpotService;
        this.userService = userService;
    }

    @GetMapping
    public String userHomePage(Model model, @AuthenticationPrincipal LoggedUser loggedUser) {
        User user = loggedUser.getUser();
        model.addAttribute("user", user);
        model.addAttribute("noteSpots", noteSpotService.getLast5EntriesByUser(user));
        return "user/home";
    }

}
