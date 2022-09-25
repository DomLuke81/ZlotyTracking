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

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable long id) {
        model.addAttribute("noteSpotDto", new NoteSpotDto());
        model.addAttribute("noteTypes", noteTypeService.getDenominations());
        return "user/noteForm";
    }


    @PostMapping("/edit/{id}")
    @ResponseBody
    public String saveNoteSpot(@Valid NoteSpotDto noteSpotDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/noteForm";
        }
        return noteSpotDto.getNoteSerialNumber() + "<br/>";
    }
}
