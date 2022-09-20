package pl.domluke.zlotytracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.domluke.zlotytracking.service.LocationZipCodeService;
import pl.domluke.zlotytracking.service.NoteTypeService;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {
    private final LocationZipCodeService locationZipCodeService;
    private final NoteTypeService noteTypeService;

    @Autowired
    public AdminPanelController(LocationZipCodeService locationZipCodeService, NoteTypeService noteTypeService) {
        this.locationZipCodeService = locationZipCodeService;
        this.noteTypeService = noteTypeService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("zipCodes", locationZipCodeService.getLast5Entries());
        model.addAttribute("noteTypes", noteTypeService.getLast5Entries());
        return "admin/home";
    }

}
