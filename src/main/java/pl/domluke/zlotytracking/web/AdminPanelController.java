package pl.domluke.zlotytracking.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {

    @GetMapping
    @ResponseBody
    public String home() {
        return "to jest strona admina.";
    }

}
