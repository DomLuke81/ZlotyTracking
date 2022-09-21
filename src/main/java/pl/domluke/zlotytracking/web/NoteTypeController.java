package pl.domluke.zlotytracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.domluke.zlotytracking.domain.NoteType;
import pl.domluke.zlotytracking.service.NoteTypeService;

@Controller
@RequestMapping("/admin/noteTypes")
public class NoteTypeController {
    private final NoteTypeService noteTypeService;

    @Autowired
    public NoteTypeController(NoteTypeService noteTypeService) {
        this.noteTypeService = noteTypeService;
    }

    @GetMapping("")
    public String home() {
        return "forward:/admin/noteTypes/page/1";
    }

    @GetMapping("/page/{page}")
    public String homePaged(Model model, @PathVariable Integer page) {
        Page<NoteType> noteTypePage = noteTypeService.getAllOnPages((page - 1), 5);
        model.addAttribute("noteType", noteTypePage.getContent());
        model.addAttribute("page", noteTypePage.getNumber());
        model.addAttribute("pages", noteTypePage.getTotalPages());
//        model.addAttribute("page", noteTypeService.getAllOnPages((page - 1), 20));
        return "noteType/home";
    }
}
