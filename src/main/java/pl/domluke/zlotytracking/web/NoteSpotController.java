package pl.domluke.zlotytracking.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.domluke.zlotytracking.domain.NoteSpotDto;
import pl.domluke.zlotytracking.service.LocationZipCodeService;
import pl.domluke.zlotytracking.service.NoteTypeService;

import javax.validation.Valid;

@Controller
@RequestMapping("user/notes")
public class NoteSpotController {
    private final NoteTypeService noteTypeService;
    private final LocationZipCodeService locationService;

    public NoteSpotController(NoteTypeService noteTypeService, LocationZipCodeService locationService) {
        this.noteTypeService = noteTypeService;
        this.locationService = locationService;
    }

    public void loadDataForFormToModel(Model model) {
        model.addAttribute("noteTypes", noteTypeService.getDenominations());
        model.addAttribute("voivodeships", locationService.getVoivodeships());
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable long id) {
        model.addAttribute("noteSpotDto", new NoteSpotDto());
        loadDataForFormToModel(model);
        return "user/noteForm";
    }

    @PostMapping("/edit/{id}")
    public String saveNoteSpot(@Valid NoteSpotDto noteSpotDto, BindingResult bindingResult, Model model) {
        noteSpotDto.setNoteSerialNumber(noteSpotDto.getNoteSerialNumber().toUpperCase());
        if (bindingResult.hasErrors()) {
            loadDataForFormToModel(model);
            model.addAttribute("message", noteSpotDto.getDenominationRadios());
            return "user/noteForm";
        }
        //save
        model.addAttribute("message", "zapisano");
        return "user/noteForm";
    }
}
