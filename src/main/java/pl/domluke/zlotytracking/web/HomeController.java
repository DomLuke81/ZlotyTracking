package pl.domluke.zlotytracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.domluke.zlotytracking.service.NoteSpotService;

@Controller
@RequestMapping("")
public class HomeController {
    private final NoteSpotService noteSpotService;

    @Autowired
    public HomeController(NoteSpotService noteSpotService) {
        this.noteSpotService = noteSpotService;
    }

    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("noteSpots",
                noteSpotService.getAllSpotsSortedByDateDescOnPages(0, 10));
        model.addAttribute("noteHits",
                noteSpotService.getAllHitsSortedByDateDescOnPages(1, 0, 10));
        return "home";
    }

    @GetMapping("/notes/{id}")
    public String showNote(@PathVariable long id, Model model) {
        model.addAttribute("note", noteSpotService.getNoteBySpotIdWithHiddenPoles(id));
        return "note/show";
    }
}
