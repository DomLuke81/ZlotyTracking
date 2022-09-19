package pl.domluke.zlotytracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.domluke.zlotytracking.service.LocationZipCodeService;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {
    private final LocationZipCodeService locationZipCodeService;

    @Autowired
    public AdminPanelController(LocationZipCodeService locationZipCodeService) {
        this.locationZipCodeService = locationZipCodeService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("zipCodes", locationZipCodeService.getLast5Entries());
        return "admin/home";
    }

}
