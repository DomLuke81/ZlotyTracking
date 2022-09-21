package pl.domluke.zlotytracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.domluke.zlotytracking.domain.NoteTypeDto;
import pl.domluke.zlotytracking.service.NoteTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
        model.addAttribute("page", noteTypeService.getAllOnPages((page - 1), 5));
        return "noteType/home";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable int id) {
        model.addAttribute("noteTypeDto", noteTypeService.getNoteTypeById(id));
        return "noteType/form";
    }

    @PostMapping("/edit/{id}")
    public String editNoteType(@Valid NoteTypeDto noteTypeDto,
                               BindingResult bindingResult,
                               @RequestParam MultipartFile image,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "noteType/form";
        }
        if (!noteTypeService.saveWithoutImage(noteTypeDto)) {
            model.addAttribute("message", "Błąd. Nie zapisano rekordu!");
            return "noteType/form";
        }
        if (!image.isEmpty()) {
//            if (!noteTypeService.saveImage(noteTypeDto.getId())) {
            if (true) {
                model.addAttribute("message", "Błąd. Nie zapisano pliku z obrazem!");
                return "noteType/form";
            }
        }
        return "redirect:/admin/noteTypes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, HttpServletRequest request) {
        noteTypeService.delete(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
