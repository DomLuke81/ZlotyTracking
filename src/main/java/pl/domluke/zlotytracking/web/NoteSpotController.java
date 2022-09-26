package pl.domluke.zlotytracking.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.domluke.zlotytracking.domain.NoteSpotDto;
import pl.domluke.zlotytracking.service.NoteTypeService;

import javax.validation.Valid;

@Controller
@RequestMapping("user/notes")
public class NoteSpotController {
    private final NoteTypeService noteTypeService;

    public NoteSpotController(NoteTypeService noteTypeService) {
        this.noteTypeService = noteTypeService;
    }

    public void loadDataForFormToModel(Model model) {
        model.addAttribute("noteTypes", noteTypeService.getDenominations());
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable long id) {
        model.addAttribute("noteSpotDto", new NoteSpotDto());
        loadDataForFormToModel(model);
        return "user/noteForm";
    }


    @PostMapping("/edit/{id}")
    public String saveNoteSpot(@Valid NoteSpotDto noteSpotDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            loadDataForFormToModel(model);
            model.addAttribute("message", noteSpotDto.getNoteTypeDto());
            return "user/noteForm";
        }
        model.addAttribute("message", noteSpotDto.getNoteTypeDto().getDenomination());
        return "user/noteForm";
    }
}
